# Microservice Project Documentation

## Abstract

we want to build an online system for delivering packages through drones (delivery drones), as a service. 
Basic need: a user wants to send a package (of some weight) from one place to other place, on some specific date/time 
(including immediately), within some amount of time. The user may want to track the delivering process, knowing exactly 
where the package is and how much time is left to complete the delivery.

## Overview

This project is a microservice-based system for managing package deliveries using drones. It consists of three main services:

- **Customer Service**: Handles order creation and exposes a REST API for customers.
- **Delivery Service**: Consumes orders and coordinates delivery requests to drones.
- **Drone Service**: Manages drone dispatch and delivery status.

The services communicate asynchronously via RabbitMQ message queues. Traefik is used as an API gateway and reverse proxy.

## Architecture

- **API Gateway**: Traefik routes requests to the appropriate service.
- **Message Broker**: RabbitMQ facilitates communication between services.
- **Microservices**: Each service is independently deployable and scalable.

```
[Customer Service] --(REST)--> [Traefik] --(REST)--> [Customer Service]
[Customer Service] --(AMQP)--> [RabbitMQ] --(AMQP)--> [Delivery Service] --(AMQP)--> [Drone Service]
```

## Domain Model

- **Customer**: User who requests a package delivery.
- **Package**: The item to be delivered.
- **Drone**: Entity responsible for transporting packages.
- **Delivery**: The process of moving a package from source to destination.

## Services

### Customer Service
- Exposes `/api/orders` endpoint for order creation.
- Publishes order messages to RabbitMQ.

### Delivery Service
- Listens for order messages from RabbitMQ.
- Forwards delivery requests to the Drone Service.

### Drone Service
- Manages drone state and assignment.
- Provides `/api/drones` endpoint for drone status.

## Deployment

- All services are containerized with Docker.
- `docker-compose.yml` orchestrates multi-container setup.
- Traefik and RabbitMQ are included as infrastructure services.

## Configuration

- Each service has its own `application.yml` for configuration.
- RabbitMQ connection details are set via environment variables.

## Build & Run

- Use `./gradlew build` to build all services.
- Use `docker-compose up` to start the full stack.

## Further Details

- See `docs/design.md` for architecture diagrams.
- See `docs/domain.md` for domain model and glossary.
- See `docs/report.md` for the project report.