FROM openjdk:8

VOLUME /temp

COPY ./target/user-service-0.1.0.jar app.jar

CMD ["java","-jar","app.jar"]

