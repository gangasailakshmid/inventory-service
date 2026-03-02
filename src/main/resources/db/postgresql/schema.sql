CREATE TABLE IF NOT EXISTS inventory (
  id BIGSERIAL PRIMARY KEY,
  product_code VARCHAR(40) NOT NULL UNIQUE,
  available_inventory INTEGER NOT NULL,
  dc_inventory INTEGER NOT NULL,
  intransit_inventory INTEGER NOT NULL,
  store_inventory INTEGER NOT NULL,
  version BIGINT
);

CREATE INDEX IF NOT EXISTS idx_inventory_product_code ON inventory(product_code);
CREATE INDEX IF NOT EXISTS idx_inventory_available ON inventory(available_inventory);
