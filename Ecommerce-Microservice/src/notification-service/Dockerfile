FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar notification-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","notification-0.0.1-SNAPSHOT.jar"]
