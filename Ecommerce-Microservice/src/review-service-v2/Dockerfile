FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar review-v2-2.0.jar
EXPOSE 8091
ENTRYPOINT ["java","-jar","review-v2-2.0.jar"]
