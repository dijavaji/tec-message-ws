#define base docker image
FROM amazoncorretto:17-alpine-jdk

WORKDIR /app

COPY target/*.jar /app/application.jar

EXPOSE 8080

#CMD ["java", "-jar", "tec-account-ws-0.0.1-SNAPSHOT.jar"]
LABEL maintainer="technoloqie.com.ec"
#ADD target/tec-chatbot-ws-0.0.1-SNAPSHOT.jar tec-chatbot-ws.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
