# Dockerfile para la aplicación
FROM openjdk:17-jdk-slim

# Copia el archivo JAR de tu aplicación
COPY GymTracker-0.0.1-SNAPSHOT.jar /app/app.jar

# Copia el script wait-for-it
COPY wait-for-it.sh /app/wait-for-it.sh

# Para que el script wait-for-it.sh tenga permisos de ejecución
RUN chmod +x /app/wait-for-it.sh

# Establecer el directorio de trabajo
WORKDIR /app

# Comando para iniciar la aplicación, esperando a que la base de datos esté lista
CMD ["./wait-for-it.sh", "database:3306", "--", "java", "-jar", "app.jar"]
