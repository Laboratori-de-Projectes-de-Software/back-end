FROM maven:3.9.9-eclipse-temurin-17 AS builder
WORKDIR /usr/src/lab/
COPY src src
COPY pom.xml .
RUN mvn -DskipTests --batch-mode clean package

FROM openjdk:17
WORKDIR /usr/src/lab/
COPY --from=builder /usr/src/lab/target/*.jar lab.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "lab.jar"]