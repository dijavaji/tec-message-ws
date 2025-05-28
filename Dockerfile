# Etapa de construccion
FROM maven:latest AS build

WORKDIR /app

# Copiar el archivo pom.xml y el directorio src para la construccion
COPY pom.xml .
COPY src ./src

# Construir la aplicacion
RUN mvn clean package -DskipTests

# Etapa de ejecucion
#define base docker image
FROM amazoncorretto:17-alpine-jdk

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el JAR construido desde la etapa de construccion
COPY --from=build /app/target/tec-message-ws-0.0.1-SNAPSHOT.jar .
#COPY target/*.jar /app/application.jar

EXPOSE 8080

LABEL maintainer="technoloqie.com.ec"

ENTRYPOINT ["java", "-jar", "tec-message-ws-0.0.1-SNAPSHOT.jar"]

