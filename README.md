# University Management System - Lab 8

This project is a microservice-based university management system prepared for WM2 Lab 8. It contains separate services for student management and course management, including enrollment, prerequisite validation, and OpenAPI documentation in Azerbaijani.

## Project Overview

The system supports:

- creating, updating, listing, and deleting students
- creating, updating, listing, and deleting courses
- enrolling a student into a course
- storing the enrollment date
- validating prerequisite courses before enrollment
- retrieving courses by student name
- viewing API documentation through Swagger UI

## Technologies Used

- Java 21
- Spring Boot 3
- Spring Web
- Spring Data JPA
- PostgreSQL
- OpenFeign
- Springdoc OpenAPI / Swagger UI
- Docker Compose
- Gradle

## Services

- `student-service` runs on `http://localhost:9090`
- `course-service` runs on `http://localhost:8081`

## Database and Service Startup

### Option 1: Run with Docker Compose

Start databases and both services:

```bash
docker compose up --build
```

This starts:

- PostgreSQL for students on port `5432`
- PostgreSQL for courses on port `5433`
- `student-service` on port `9090`
- `course-service` on port `8081`

### Option 2: Run services manually

First start PostgreSQL databases. The default local configuration expects:

- `studentDB` on `localhost:5432`
- `courseDB` on `localhost:5433`
- username: `postgres`
- password: `password`

Then run the services in separate terminals:

```bash
./gradlew :student-service:bootRun
```

```bash
./gradlew :course-service:bootRun
```

## Swagger URLs

- Student Service Swagger UI: `http://localhost:9090/swagger-ui/index.html`
- Course Service Swagger UI: `http://localhost:8081/swagger-ui/index.html`

## Main Endpoints

### Student Service

- `POST /api/v1/students`
- `GET /api/v1/students`
- `GET /api/v1/students/{id}`
- `PUT /api/v1/students/{id}`
- `DELETE /api/v1/students/{id}`
- `GET /api/v1/students/search?name=Nicat`

### Course Service

- `POST /api/v1/courses`
- `GET /api/v1/courses`
- `GET /api/v1/courses/{id}`
- `PUT /api/v1/courses/{id}`
- `DELETE /api/v1/courses/{id}`
- `POST /api/v1/courses/{courseId}/students/{studentId}`
- `GET /api/v1/courses/{courseId}/students`
- `GET /api/v1/courses/by-student-name?name=Nicat`

## Example Requests

Create a student:

```bash
curl -X POST http://localhost:9090/api/v1/students \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Nicat",
    "lastName": "Aliyev",
    "email": "nicat.aliyev@example.com",
    "age": 20
  }'
```

Create a course without prerequisite:

```bash
curl -X POST http://localhost:8081/api/v1/courses \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Introduction to Programming",
    "code": "CS101",
    "credits": 5,
    "prerequisiteCourseId": null
  }'
```

Create a course with prerequisite:

```bash
curl -X POST http://localhost:8081/api/v1/courses \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Data Structures",
    "code": "CS201",
    "credits": 4,
    "prerequisiteCourseId": 1
  }'
```

Enroll a student into a course:

```bash
curl -X POST http://localhost:8081/api/v1/courses/2/students/1
```

List students enrolled in a course:

```bash
curl http://localhost:8081/api/v1/courses/2/students
```

Get courses by student name:

```bash
curl "http://localhost:8081/api/v1/courses/by-student-name?name=Nicat"
```

## How to Test the Required Features

1. Create a student.
2. Create a prerequisite course, for example `CS101`.
3. Create another course whose `prerequisiteCourseId` points to `CS101`.
4. Try enrolling the student into the second course before enrolling into `CS101`.
5. Confirm that the request is rejected with a meaningful error message.
6. Enroll the same student into `CS101`.
7. Enroll the student into the second course again.
8. Confirm that the response includes `enrollmentDate`.
9. Search courses by the student's name using `/api/v1/courses/by-student-name`.
10. Open Swagger UI and verify the Azerbaijani documentation.

## Important Notes

- `prerequisiteCourseId` is optional and may be `null`.
- A course cannot reference itself as a prerequisite.
- Enrollment is rejected if the prerequisite course has not already been completed through enrollment.
- Swagger descriptions are written in Azerbaijani as required by the lab.
- The project uses `spring.jpa.hibernate.ddl-auto=update`, so tables are created or updated automatically.