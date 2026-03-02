package com.oms.inventory_service.dto;

public record InventoryResponse(
		Long id,
		String productId,
		String productCode,
		Integer availableInventory,
		Integer dcInventory,
		Integer intransitInventory,
		Integer storeInventory
) {
}
