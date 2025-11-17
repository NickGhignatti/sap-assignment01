# Deployment Guide

## Prerequisites
- Docker and Docker Compose installed
- Java 17 and Gradle (for local builds)

## Build Steps
1. Build all services:
   ```sh
   ./gradlew build
   ```
2. Build Docker images (optional, handled by docker-compose):
   ```sh
   docker-compose build
   ```

## Running the System
1. Start all services and infrastructure:
   ```sh
   docker-compose up
   ```
2. Access Traefik dashboard at [http://localhost:8081](http://localhost:8081)
3. Access RabbitMQ management at [http://localhost:15672](http://localhost:15672)

## Stopping the System
- To stop all containers:
  ```sh
  docker-compose down
  ```

## Configuration
- Service configuration is managed via `application.yml` files and environment variables.
- RabbitMQ credentials and host can be set in the environment or `.env` file.

## Troubleshooting
- Check logs with `docker-compose logs <service>`
- Ensure ports 8080, 8081, 15672, and 5672 are available
- Health endpoints:
  - Customer: `/api/orders/health`
  - Drone: `/api/drones/health` (if implemented)
