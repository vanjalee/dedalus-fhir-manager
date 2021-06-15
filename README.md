# dedalus-fhir-manager project

To startup the application and run tests, follow these steps:

1. Checkout the project and navigate to the project root.

2. Use either 
*mvn clean package*
or
*./mvnw package*
to build the project and run tests.

3. Prepare the docker images by running following commands in the project root:
*docker-compose build*
(Builds the quarkus application image along with JVM and Quarkus server)

4. Fire it all up:
*docker-compose up*
(Builds the MySQL and phpMyAdmin images, and fires all of the up after placing them all onto the same docker network called *quarkus*)

5. During startup of the database, database create script is copied to a location where it will be executed on database startup. This script creates the table structure and is only executed if there exist no such tables at the moment of startup.

6. Database files survive the restart of the container since volume *quarkus-db* has been declared for the mysql service

7. Open *http://localhost:8080* in browser to see the index page containing description of all endpoints

8. Open *http://localhost:9000* in browser and log in into phpMyAdmin using following credentials: root, admin

9. Send requests to the application using postman, or some other HttpClient





## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```
> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```
The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/dedalus-fhir-manager-1.0.0-SNAPSHOT-runner`
