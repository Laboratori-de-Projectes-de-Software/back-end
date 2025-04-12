FROM openjdk:17
WORKDIR /app
COPY . .
RUN sed -i 's/\r$//' mvnw
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests
CMD ["java", "-jar", "target/iasuperleague-0.0.1-SNAPSHOT.jar"]
