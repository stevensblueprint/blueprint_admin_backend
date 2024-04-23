FROM openjdk:17-slim

WORKDIR /app

COPY gradlew /app/
COPY gradle /app/gradle
COPY build.gradle /app/

RUN ./gradlew --no-daemon dependencies

COPY build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
