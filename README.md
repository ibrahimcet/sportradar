# sportradar
Live Football World Cup Score Board

This application allows users to manage match information in scoreboard.

Users are able to start a new match, update score, finish match and scoreboard listing with all matches sorted by total score.

##Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

## Prerequisites
For building and running the application you need:
- [OpenJDK 11](https://adoptopenjdk.net/?variant=openjdk11&jvmVariant=hotspot)
- [Maven 3.6](https://maven.apache.org)
- Mongodb Server

## Run the Application Locally
Run the application on terminal as follows:
```
java -Dserver.port=9090 -jar target/sportradar-0.0.1-SNAPSHOT.jar
```
Or run the application with your preferred IDE, port must be added in VM options:
```
-Dserver.port=9090
```

## Test Coverage
I added Jacoco plugin for the test coverage.
Open terminal located at bottom left in intellij and run this command 
-mvn clean install
Then go to target -> site -> jacoco -> index.html(right click to open in browser)
Total coverage is %87 right now.

## Swagger UI
Swagger UI is accessible at http://localhost:9090/swagger-ui/

## Postman Collections
You can find my postman collections under the resources->postman folder. You can easily import and test endpoints.
