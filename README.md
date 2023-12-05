# foundever-technical-backend-test

This repository contains the result of technical backend, a microservice built for the REST API using hexagonal architecture, Spring Boot, and Java 17.

## Table of Contents

- [Description](#description)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Configuration](#configuration)
- [API Documentation](#api-documentation)

## Description

The project  is a microservice designed  to create, read and modify resources

## Getting Started

### Prerequisites

To run the foundever-technical-backend-test Project, make sure you have the following installed on your system:
- Java Development Kit (JDK) 17
- Maven


#### Database Setup

-Create the H2 database:
Add dependency; 
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
### Installation

1.	Clone the repository:
      git clone https://github.com/fatyThr/foundever-technical-backend-test.git
2.  Build the project using Maven:
> cd foundever-technical-backend-test
> mvn clean install

### Configuration

To start the foundever-technical-backend-test, use the following command:
> java -jar foundever-technical-backend-test.jar

## API Documentation

For detailed information on available APIs and endpoints, refer to the API documentation at docs/api.md.

Swagger UI Documentation: http://localhost:8080/api-docs

 


