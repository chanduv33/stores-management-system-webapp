FROM openjdk:8-jdk-alpine
COPY target/stores-management-system-webapp-0.0.1-SNAPSHOT.jar storesapplication.jar
ENTRYPOINT ["java","-jar","storesapplication.jar"]