FROM openjdk:8-jdk-alpine
LABEL MAINTAINER="Ambarish Rao <ambarish.v.rao@gmail.com.com>"
EXPOSE 8080
ARG JAR_FILE=target/data-api-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]