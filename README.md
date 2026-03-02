# Inventory Service

Spring Boot microservice for inventory availability and stock movement tracking.

## Service Scope
- Manage inventory by `productId`/`productCode`

## Tech Stack
- Java 25
- Spring Boot 4
- Spring Data JPA
- H2 / PostgreSQL
- OpenAPI (Swagger)

## Default Port
- `8083`

## Architecture Flow
```mermaid
flowchart LR
    C[Client] --> API[Inventory Service API]
    API --> CT[Inventory Controller]
    CT --> SV[Inventory Service]
    SV --> RP[Inventory Repository]
    RP --> DB[(H2 / PostgreSQL)]
    SV --> EH[Global Exception Handler]
```

## Sequence Diagram
```mermaid
sequenceDiagram
    participant U as API Client
    participant C as InventoryController
    participant S as InventoryService
    participant R as InventoryRepository
    participant D as Inventory DB
    participant E as Exception Handler

    U->>C: PUT /api/v1/inventory/{productId}
    C->>S: validate request
    S->>R: findByProductCode(productId)
    R->>D: select inventory
    D-->>R: row
    R-->>S: entity
    S->>R: save(updated entity)
    R->>D: update inventory
    D-->>R: updated row
    R-->>S: entity
    S-->>C: InventoryResponse
    C-->>U: HTTP 200

    alt product inventory not found / validation error
      S-->>E: throw exception
      E-->>U: HTTP 4xx/5xx
    end
```

## Database Schema
- `inventory` (unique `product_code`)

### ER Diagram
```mermaid
flowchart TD
  INV[inventory]
```

## Key APIs
- `GET /api/v1/inventory`
- `GET /api/v1/inventory/{productId}`
- `POST /api/v1/inventory`
- `PUT /api/v1/inventory/{productId}`
- `DELETE /api/v1/inventory/{productId}`

## Build and Run
```bash
./gradlew clean build
./gradlew bootRun
```

Run with PostgreSQL profile:
```bash
./gradlew bootRun --args='--spring.profiles.active=postgres'
```

## API Docs
- Swagger: `http://localhost:8083/swagger-ui.html`
- OpenAPI: `http://localhost:8083/api-docs`
- 