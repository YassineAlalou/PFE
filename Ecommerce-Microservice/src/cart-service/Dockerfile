FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar cart-0.0.1-SNAPSHOT.jar
EXPOSE 8092
ENTRYPOINT ["java","-jar","cart-0.0.1-SNAPSHOT.jar"]
