# Welcome to SalomaoTech

SalomaoTech is a company that develops products for all its customers' needs. Present in several parts of the world including Brazil.

# Introducing TeamUser

TeamUser is a new product that allows you to control the user by team. Each user has a role in a team, however the user can be part of zero or more teams. Each
team has one user as a team lead. 

Initially there are 4 roles Developer, Product Owner, Tester and Team Leader. Although it's possible to create more types.

## Requirements

The project requires [Java 11](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html) or
higher.

The project makes use of Apache Maven 3.6.3 (https://maven.apache.org/docs/3.6.3/release-notes.html) 

We use a database H2 in memory (https://www.h2database.com/html/main.html)

You just need to install Java 11 and Maven 3.6.3.

### Build the project

```console
$ ./mvn clean install
```

### Run the project

The application will start on port `8080`.
```console
$ ./mvn spring-boot:run
```

### Run the tests
```console
$ ./mvn test
```

## API

Below is a list of the main API endpoints.

### Create a new role

Endpoint

```text
POST /api/roles
```

Example of body

```json
{
    "name": "ARCHITECT"
}
```

Response
HTTP Status 200

### Assign a role to a team member

Endpoint

```text
POST /api/team-users
```

Example of body

gianniWehner - fd282131-d8aa-4819-b0c8-d9e0bfb1b75c	
TESTER - ae7a6dba-a074-4df6-bc64-6c8776d43210	
Ordinary Coral Lynx - 7676a4bf-adfe-415c-941b-1739af07039b	

```json
{
    "idUser": "fd282131-d8aa-4819-b0c8-d9e0bfb1b75c",
    "idTeam": "7676a4bf-adfe-415c-941b-1739af07039b",
    "idRole": "ae7a6dba-a074-4df6-bc64-6c8776d43210"
}
```

Response
HTTP Status 201

### Look up a role for a membership

Endpoint

```text
GET /api/team-users/team/{idTeam}/user/{idUser}
```

Example of request
/api/team-users/team/7676a4bf-adfe-415c-941b-1739af07039b/user/fd282131-d8aa-4819-b0c8-d9e0bfb1b75c

gianniWehner - fd282131-d8aa-4819-b0c8-d9e0bfb1b75c	
TESTER - ae7a6dba-a074-4df6-bc64-6c8776d43210	
Ordinary Coral Lynx - 7676a4bf-adfe-415c-941b-1739af07039b	

Response
```json
{
  "user": {
    "id": "fd282131-d8aa-4819-b0c8-d9e0bfb1b75c",
    "name": null
  },
  "team": {
    "id": "7676a4bf-adfe-415c-941b-1739af07039b",
    "name": null
  },
  "role": {
    "id": "ae7a6dba-a074-4df6-bc64-6c8776d43210",
    "name": "TESTER"
  }
}
```
HTTP Status 201

### Look up memberships for a role

Endpoint

```text
GET /api/team-users/role/{idRole}
```

Example of request
/api/team-users/role/ae7a6dba-a074-4df6-bc64-6c8776d43210

TESTER - ae7a6dba-a074-4df6-bc64-6c8776d43210	

Response
```json
[
  {
    "user": {
      "id": "fd282131-d8aa-4819-b0c8-d9e0bfb1b75c",
      "name": "gianniWehner"
    },
    "team": {
      "id": "7676a4bf-adfe-415c-941b-1739af07039b",
      "name": "Ordinary Coral Lynx"
    },
    "role": {
      "id": "ae7a6dba-a074-4df6-bc64-6c8776d43210",
      "name": "TESTER"
    }
  }
]
```
HTTP Status 201

### Suggestions for improvement
- Control apis access by user type
- Use the application and database in docker
- Implement data auditing with envers (https://docs.jboss.org/envers/docs/)
- Implement checkstyle and integration with sonar.

