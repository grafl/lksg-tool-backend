# docker build -t lksg-tool-backend:0.0.1-SNAPSHOT .

FROM eclipse-temurin:18.0.1_10-jre-alpine

VOLUME /tmp

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} lksg-tool-backend.jar

EXPOSE 8888

# ENTRYPOINT ["java", "-jar","/lksg-tool-backend.jar"]
ENTRYPOINT ["java", "-Dspring.profiles.active=local","-jar","/lksg-tool-backend.jar"]
