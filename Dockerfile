FROM gradle:7.6.1-jdk17-alpine AS build
WORKDIR /app
COPY . .
RUN gradle build -x test --no-daemon

# Run stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Create a non-root user
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# Copy the jar file from build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Environment variables for database connection
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/blueprint
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]