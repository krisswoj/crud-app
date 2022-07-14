# Simple CRUD Application
In this application you can do all CRUD operations by restApi or Angular GUI.

**Used technologies**: Java, Spring Boot, Angular, PostgreSQL (with Flyway), Heroku, Travis-CI (Partial CI/CD), Docker, DockerHub, Swagger. Written unit and integration (Checks CRUD with containerTest DB) tests.

[![Build Status](https://app.travis-ci.com/krisswoj/crud-app.svg?branch=main)](https://app.travis-ci.com/krisswoj/crud-app)

## API (Swagger)
https://krisswoj-crud-app-main.herokuapp.com/swagger-ui/#/

## How to run

To run locally you have to execute following command:


```bash
docker-compose up --build
```

The backend application will launch at http://localhost:8080/ and the frontend at http://localhost:4200/
