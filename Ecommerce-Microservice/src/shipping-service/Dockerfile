FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar shipping-0.0.1-SNAPSHOT.jar
EXPOSE 8093
ENTRYPOINT ["java","-jar","shipping-0.0.1-SNAPSHOT.jar"]
