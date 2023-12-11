# task-manager

This is an application(backend) for creating, deleting and editing employee tasks.

# IntelliJ IDE

JetBrains provide a the IntelliJ IDE.

This IDE is recommended for excellent Maven integration, and very fast build times.

# Tools
 * Java 17
 * Spring framework (boot, data, mvc, logging, test, security)
 * Maven
 * Lombok
 * PostgreSQL
 * Liquibase
 * Mapstruct
 * Swagger
 * JUnit 5

# Dependencies

All required dependencies are listed in the 'pom.xml' file.

## Setting up
1. Open project in IntelliJ, it will create an `.idea`.
2. Use *File* > *Project Structure* to confirm Java 17 is used.
3. Create *Edit Configuration* (if not exist Add new *Maven* configuration) or check build and run options(must be specified Java 17 SDK for 'task-manager' module and 'com.dannyhromau.task.manager.Application' as running directory).
4. Use the *Maven* tools window to:
   * *Toggle "Skip Tests" Mode* (if You won't to testing the application)
   * *Execute Maven Goal*: `clean install`
   * check the target directory (You should see an archive there named *task-manager-1.0-SNAPSHOT.jar*).

## Db settings
This project uses postgreSQL database.
Before each create database(for example *postgres*).
Connection settings are specified by *application.yml* file (for using custom settings change required fields in this file).
For initialization, you must sequentially use SQL queries specified in the *initDB.sql* file (resource folder).
Tables, fields, keys and indexes creates by liquibase changelog files.

## Security
1. Application security based on OAuth 2.0 protocol and JWT authentication
2. File application.yml contains a lot of security properties You can configure (for example you can specify tokens expiration time) 
     
## Running and testing
1. Run the application with command `java -jar microservice-employee-1.0-SNAPSHOT.jar --spring.config=application` in *command line*.
2. For tetsing all the endpoints possible see swagger user interface go to "<hostname>/swagger-ui/index.html#/".

❗️By default, You can see api docs in your browser as guest, but, for safety, You can close your docs, if You remove url patterns from 'securlpattern' config in application.yml file.
 ```html
 securlpattern:
  urls:
    - /api/v1/auth/register
    - /api/v1/auth/login
    - /swagger-ui/* remove or comment this pattern
    - /v3/api-docs/** remove or comment this pattern
 ```

## Docker
1. You can create your custom image using the command  `docker build -t <image name>` .
2. If You want to change default docker properties, edit Dockerfile file located in the project folder 
3. Running the container for example `docker run --name <conatiner name> -p 8081:8081 <image name name>`
Note: successful running the container depends on another infrastructure (working docker network, connection with db and other)

Hope You enjoy!
