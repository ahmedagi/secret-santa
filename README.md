# Secret Santa MVC App

## Overview

This is a simple Secret Santa web application built using Spring Boot with MVC architecutre.
It allows employees to be added and randomly paired for a Secret Santa event.
The app provides both server-side rendering (SSR) using Thymeleaf and API endpoints for managing employees.

## Live Link

The live version of the app is available at:
[Live Link](https://secret-santa-production-5911.up.railway.app/)

## Tech Stack

- **Backend:** Spring Boot
- **Frontend:** Thymeleaf
- **Database:** PostgreSQL

## Features

- View the list of employees
- Add new employees
- Delete employees
- View the list of assigned Secret Santa pairs
- Generate a new list of Secret Santa pairs

## Installation & Setup

### Prerequisites

Ensure you have the following installed:

- Java 17
- Maven
- PostgreSQL

### Cloning the Repository

Download the project by running:

```bash
git clone https://github.com/ahmedagi/secret-santa.git
cd secret-santa
```

### Configuration

*NOTE: You can skip this step if you want to provide the environment variables directly when starting the app.*

Set up the PostgreSQL database and update the following properties inside the `application.properties` file:

```properties
spring.datasource.url=your_db_url
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
# Optional - defaults to 8080
server.port=your_server_port
```

### Building the Application

Run the following command to build the project:

```
mvn clean package
```

### Running the Application Locally

#### Option 1: Using application.properties

If you updated the properties in the `application.properties` file, start the application with:

```
java -jar target/secret-santa-0.0.1-SNAPSHOT.jar
```

#### Option 2: Using Environment Variables (inline command)

##### cmd:

```cmd
set DB_USERNAME=your_db_username 
set DB_PASSWORD=your_db_password
set DB_URL=your_db_url
set PORT=your_server_port
java -jar target/secret-santa-0.0.1-SNAPSHOT.jar
```

*(You can omit the `set PORT=your_server_port` line to use the default port 8080)*

##### bash/git bash

```bash
DB_USERNAME=your_db_username \
DB_PASSWORD=your_db_password \
DB_URL=your_db_url \
PORT=your_server_port \
java -jar target/secret-santa-0.0.1-SNAPSHOT.jar
```

*(You can omit the `PORT=your_server_port \` line to use the default port 8080)*

## API Documentation

### SSR Routes

| Method | Route      | Description                                      |
|--------|------------|--------------------------------------------------|
| GET    | /          | Displays the list of assigned Secret Santa pairs |
| POST   | /          | Generates a new list of random pairs             |
| GET    | /employees | Displays all employees                           |

### API Endpoints

| Method | Route           | Description          | Request Body (JSON)    |
|--------|-----------------|----------------------|------------------------|
| POST   | /employees      | Adds a new employee  | { "name": "John Doe" } |
| DELETE | /employees/{id} | Removes and employee | N/A                    |

## Project Structure

```
src
└── main
    ├── java 
    │   ├── config
    │   ├── model
    │   ├── controller
    │   │   └── advice
    │   ├── service
    │   ├── repository
    │   └── exception
    │
    └── resources
        ├── static
        │   ├── css
        │   └── js
        ├── templates
        │   ├── error
        │   └── fragments
        └── application.properties
```

## Future Improvements

- Authentication and authorization
- Tests