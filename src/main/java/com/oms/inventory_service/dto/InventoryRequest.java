package com.oms.inventory_service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record InventoryRequest(
		@NotBlank String productId,
		@NotNull @Min(0) Integer availableInventory,
		@NotNull @Min(0) Integer dcInventory,
		@NotNull @Min(0) Integer intransitInventory,
		@NotNull @Min(0) Integer storeInventory
) {
}
