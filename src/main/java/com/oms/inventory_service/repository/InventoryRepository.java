package com.oms.inventory_service.repository;

import com.oms.inventory_service.entity.Inventory;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	Optional<Inventory> findByProductCode(String productCode);
}
