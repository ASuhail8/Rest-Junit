FROM openjdk:8u191-jre-alpine3.9

RUN apk add openjdk8

#set working directory
WORKDIR /restapi-junit
#Run and install maven
USER root
RUN apk update && apk add maven
#Copy all project files to container
COPY ./src /restapi-junit/src
COPY ./pom.xml /restapi-junit/pom.xml

ENTRYPOINT mvn clean test -Dtest=rest.Tests.oAuth