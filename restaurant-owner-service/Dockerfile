FROM openjdk:11
ADD ./target/restaurant-owner-service-0.0.1-SNAPSHOT.jar /usr/src/restaurant-owner-service-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "restaurant-owner-service-0.0.1-SNAPSHOT.jar"]
