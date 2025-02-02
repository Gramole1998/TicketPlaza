**TicketPlaza - Ticket Booking Microservice**

Welcome to Ticket Plaza, a microservices-based ticket booking system designed for efficient event management and ticket booking. This project uses Spring Boot and several supporting components to create a scalable, reliable architecture. This README will guide you through the project structure, prerequisites, and setup instructions.

Project Structure

API Gateway
The entry point for all incoming requests, acting as a reverse proxy and routing requests to the appropriate microservice.

Eureka Service Registry
Manages the service discovery and registration of all microservices in the system. Each service registers itself with Eureka for dynamic scaling and high availability.

Cloud Config Service
A centralized configuration service that provides configuration data for all microservices.

Event Management Service
Handles CRUD operations for event data, allowing event creation, updates, retrieval, and deletion.

Booking Service
Manages ticket booking for events, processing requests to reserve and manage tickets.

Prerequisites
Java 17

Getting Started
To set up and run Ticket Plaza locally, follow these steps in the order listed.

Do the maven clean install for each service or module.

1. Clone the Repository
2. Run the Services in Sequence
Each service in Ticket Plaza is dependent on the Eureka Service Registry and Cloud Config Service for smooth operation. Make sure to start each service in the following order:

Step 1: Start Eureka Service Registry
Step 2: Start Cloud Config Service
Step 3: Start Event Management Service
Step 4: Start Booking Service
Step 5: Start the API Gateway

To setup the SQL database in your local you need to create a seperate .properties file replicating the one that is already present in the github repo. The naming convention shouldbe followed just change the name to your name. And in the services change the spring.profiles.active=TO-YOUR-NAME .You will need to make the changes as per you system the username and password. Also create the schema event_details and ticket_booking in the sql database. These .properties files will need to be commited to the following repo https://github.com/mrushabh13/config-server-repo

Technologies Used
Java (Spring Boot)
Eureka for service discovery
Spring Cloud Config for centralized configuration management
Spring Cloud Gateway for API gateway
Spring Data JPA for database interactions
MySQL or any preferred database for data persistence
