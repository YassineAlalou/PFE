FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar review-1.0.jar
EXPOSE 8091
ENTRYPOINT ["java","-jar","review-1.0.jar"]
