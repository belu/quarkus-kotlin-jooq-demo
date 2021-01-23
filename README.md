# quarkus-kotlin-jooq-demo project

### Docker

Use Docker Compose to deploy a local PostgreSQL database and pgAdmin.

`docker-compose up`

### pgAdmin

URL: http://127.0.0.1/

Username: `admin@example.com`<br>
Password: `root`

Click on **"Add New Server"** to add the PostgreSQL database. Use the following settings:

**General**

Name: `library-db`

**Connection**

Host name/address: `postgres`<br>
Username: `postgres`<br>
Password: `postgres`

## Quarkus

Start the Quarkus application with the following command:

`mvn clean compile quarkus:dev`

## Swagger UI

Use the included Swagger UI to perform some REST calls.

http://127.0.0.1:8080/q/swagger-ui/