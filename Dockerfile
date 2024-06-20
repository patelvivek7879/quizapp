# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file to the container
COPY target/quizapp-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.postgresql.Driver
# ENV SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/postgres
# ENV SPRING_DATASOURCE_USERNAME=postgres
# ENV SPRING_DATASOURCE_PASSWORD=postgres
# ENV SPRING_JPA_PROPERTIES_HIBERNATE_DIALECT=org.hibernate.dialect.PostgreSQLDialect
# ENV SPRING_JPA_HIBERNATE_DDL-AUTO=update

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]