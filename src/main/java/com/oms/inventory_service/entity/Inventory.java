package com.oms.inventory_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "inventory", indexes = {
		@Index(name = "idx_inventory_product_code", columnList = "product_code"),
		@Index(name = "idx_inventory_available", columnList = "available_inventory")
})
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "product_code", nullable = false, unique = true, length = 40)
	private String productCode;

	@Column(name = "available_inventory", nullable = false)
	private Integer availableInventory;

	@Column(name = "dc_inventory", nullable = false)
	private Integer dcInventory;

	@Column(name = "intransit_inventory", nullable = false)
	private Integer intransitInventory;

	@Column(name = "store_inventory", nullable = false)
	private Integer storeInventory;

	@Version
	private Long version;
}
