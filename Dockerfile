FROM openjdk:17-jdk-slim

EXPOSE 8080

ADD target/FJD-42-0.0.1-SNAPSHOT.jar back.jar

ENTRYPOINT ["java","-jar","/back.jar"]