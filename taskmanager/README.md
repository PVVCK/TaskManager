# AirTribe Assignment - Task Manager - Backend API

## Overview

A collaborative task tracking system built with Spring Boot. This backend provides RESTful APIs for managing users, tasks, teams, comments, and file attachments with JWT-based authentication and role-based authorization.

## Features

- **User Registration & Authentication:** Secure registration and login with role-based access using Spring Security and JWT.

- **Task Management:** Create, update, assign, and track tasks with due dates, statuses, and priorities.

- **Team Collaboration:** Users can join teams, view team tasks, and collaborate efficiently.

- **Comments & Discussions:**  Add comments to tasks for updates, clarifications, or discussions.

- **Attachments Support:**Upload and download relevant files linked to tasks.

- **Role-Based Access Control:** Admins have full access; regular users have restricted access based on roles.

- **Activity Timestamps:** Tracks task creation and update times for better auditing.

- **Attachments Support:**Uses MySQL for persistent data storage with JPA/Hibernate.

- **API Documentation:**Swagger UI available for easy testing and documentation of all APIs.



## Technologies Used and 

- **Java 17**
- **Object-Oriented Programming (OOP)**
- **SOLID Principles**
- **Spring Boot**
- **Maven**
- **MySQL**
- **Swagger UI**
- **Spring Security using JWT**
- **Centralized Exception Handling**


## Prerequisties

- Java 15+
- IDE(STS, Eclipse, IntelliJ or VsCode)

## Note on Data Persistence 

The application uses MySQL as the primary database. In a real-world production scenario, you can configure connection pooling, caching, or database replication for performance and scalability.

## Swager UI & API Docs
Swagger UI & API Docs, Once the app is running, access the API docs:

Swagger UI: http://localhost:${server.port}/swagger-ui.html

OpenAPI JSON: http://localhost:${server.port}/v3/api-docs

## Project Structure

```bash
src
├── main/java
|          ├── com/example/taskmanager
│                                 ├── config                            # Contains all Task Manager Related Configuration's
│                                 ├── controller                        # Contains all Task Manager Related Controller's
│                                 ├── dto                               # Contains all Task Manager Related DTO's
│                                 ├── entity                            # Contains all Task Manager Related Entities
│                                 ├── enums                             # Contains all Task Manager Related Enums's
│                                 ├── exception                         # Contains all Task Manager Related Exceptions
│                                 ├── repository                        # Contains all Task Manager Related Repositories
│                                 ├── response                          # Contains all Task Manager Related Responses
│                                 ├── security                          # Contains all Task Manager Related Security
│                                 ├── service                           # Contains all Task Manager Related Service Interfaces
│                                 ├── serviceImpl                       # Contains all Task Manager Related Service Implementations
│                                 ├── TaskManagerApplication.java
├── README.md


```



