# API Documentation

## Customer Service

### POST /api/orders
- **Description:** Create a new delivery order.
- **Request Body:**
  - `customerId` (string): Customer identifier
  - `fromAddress` (string): Pickup address
  - `toAddress` (string): Delivery address
  - `packageWeight` (double): Weight of the package
  - `requestedDeliveryTime` (datetime, optional): Desired delivery time
  - `maxDeliveryTimeMinutes` (int): Maximum allowed delivery time
- **Response:**
  - `201 Created` with order details

### GET /api/orders/health
- **Description:** Health check endpoint
- **Response:**
  - `200 OK` with status message

---

## Drone Service

### GET /api/drones
- **Description:** Get status of a drone assigned to an order
- **Request Body:**
  - `orderId` (string): Order identifier
- **Response:**
  - `200 OK` with drone status or `404 Not Found`

---

## Message Queues

- **order_queue**: Orders published by Customer Service, consumed by Delivery Service
- **drone_queue**: Delivery requests sent from Delivery Service to Drone Service

---

## Common Data Structures

### OrderMessage
- `orderId` (string)
- `customerId` (string)
- `fromAddress` (string)
- `toAddress` (string)
- `packageWeight` (double)
- `requestedDeliveryTime` (datetime)
- `maxDeliveryTimeMinutes` (int)

### CreateOrderRequest
- `customerId` (string)
- `fromAddress` (string)
- `toAddress` (string)
- `packageWeight` (double)
- `requestedDeliveryTime` (datetime, optional)
- `maxDeliveryTimeMinutes` (int)
