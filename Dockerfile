FROM openjdk:17-jdk
ARG JAR-FILE=target/*.jar
COPY ./target/Sdudent-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]