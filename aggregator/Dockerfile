FROM openjdk:11
ADD ./target/Aggregator-0.0.1-SNAPSHOT.jar /usr/src/Aggregator-0.0.1-SNAPSHOT.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "Aggregator-0.0.1-SNAPSHOT.jar"]
