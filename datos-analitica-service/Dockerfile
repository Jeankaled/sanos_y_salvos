# 1. Imagen base ultraligera de Java 21 (Eficiencia)
FROM eclipse-temurin:21-jre-alpine

# 2. Creación de un usuario sin privilegios root (Seguridad)
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

# 3. Directorio de trabajo dentro del contenedor
WORKDIR /app

# 4. Copiamos el archivo .jar compilado desde la carpeta target
COPY target/*.jar app.jar

# 5. Exponemos el puerto en el que corre este microservicio
EXPOSE 8080

# 6. Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]