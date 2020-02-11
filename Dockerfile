FROM openjdk:8-jdk-alpine
LABEL maintainer="isagiongo@gmail.com"
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]

