FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar productcatalog-0.0.1-SNAPSHOT.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","productcatalog-0.0.1-SNAPSHOT.jar"]
