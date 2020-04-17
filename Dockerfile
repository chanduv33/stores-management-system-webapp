FROM openjdk:8-jdk-alpine
COPY target/stores-management-system-webapp-0.0.1-SNAPSHOT.jar storesmanagementsystem.jar
ENTRYPOINT ["java","-jar","storesmanagementsystem.jar"]