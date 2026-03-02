package com.oms.inventory_service.service;

import com.oms.inventory_service.dto.InventoryRequest;
import com.oms.inventory_service.dto.InventoryResponse;
import com.oms.inventory_service.entity.Inventory;
import com.oms.inventory_service.exception.ResourceNotFoundException;
import com.oms.inventory_service.repository.InventoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

	private final InventoryRepository inventoryRepository;

	@Transactional(readOnly = true)
	public List<InventoryResponse> getAll() {
		return inventoryRepository.findAll().stream().map(this::map).toList();
	}

	@Transactional(readOnly = true)
	public InventoryResponse getByProductId(String productId) {
		return map(findByProductCodeEntity(productId));
	}

	@Transactional
	public InventoryResponse create(InventoryRequest request) {
		Inventory inventory = new Inventory();
		apply(request, inventory);
		return map(inventoryRepository.save(inventory));
	}

	@Transactional
	public InventoryResponse update(String productId, InventoryRequest request) {
		Inventory inventory = findByProductCodeEntity(productId);
		apply(request, inventory);
		return map(inventoryRepository.save(inventory));
	}

	@Transactional
	public void delete(String productId) {
		inventoryRepository.delete(findByProductCodeEntity(productId));
	}

	private Inventory findByProductCodeEntity(String productCode) {
		return inventoryRepository.findByProductCode(productCode)
				.orElseThrow(() -> new ResourceNotFoundException("Inventory not found for productId: " + productCode));
	}

	private void apply(InventoryRequest request, Inventory inventory) {
		inventory.setProductCode(request.productId());
		inventory.setAvailableInventory(request.availableInventory());
		inventory.setDcInventory(request.dcInventory());
		inventory.setIntransitInventory(request.intransitInventory());
		inventory.setStoreInventory(request.storeInventory());
	}

	private InventoryResponse map(Inventory inventory) {
		return new InventoryResponse(
				inventory.getId(),
				inventory.getProductCode(),
				inventory.getProductCode(),
				inventory.getAvailableInventory(),
				inventory.getDcInventory(),
				inventory.getIntransitInventory(),
				inventory.getStoreInventory()
		);
	}
}
