# Dockerfile para la aplicación
FROM openjdk:17-jdk-slim
COPY GymTracker-0.0.1-SNAPSHOT.jar /app/app.jar
WORKDIR /app
CMD ["java", "-jar", "app.jar"]

