FROM openjdk:17-alpine
WORKDIR /app
COPY src/main/resources/application.yml /app/application.yml
COPY target/task-manager-1.0-SNAPSHOT.jar /app/task-manager-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/app/task-manager-1.0-SNAPSHOT.jar"]