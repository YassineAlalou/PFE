FROM openjdk:8-jdk-alpine
VOLUME /users
ADD /target/users-service-0.0.1-SNAPSHOT.jar  /users/
EXPOSE 8091
CMD  java -jar  /users/users-service-0.0.1-SNAPSHOT.jar