FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /payment
ENV SPRING_APPLICATION_NAME payment-service
ADD /target/payment-service-0.0.1-SNAPSHOT.jar  /payment/
CMD  java -jar  /payment/payment-service-0.0.1-SNAPSHOT.jar --spring.application.name=${SPRING_APPLICATION_NAME}
EXPOSE 8092