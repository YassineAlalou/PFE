FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /checkout
ENV SPRING_APPLICATION_NAME checkout-service
ADD /target/checkout-service-0.0.1-SNAPSHOT.jar  /checkout/
EXPOSE 8094
CMD  java -jar  /checkout/checkout-service-0.0.1-SNAPSHOT.jar --spring.application.name=${SPRING_APPLICATION_NAME}
