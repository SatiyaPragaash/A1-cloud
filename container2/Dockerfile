FROM maven:3.8.4-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
COPY --from=build /target/*.jar app.jar
EXPOSE 2000
ENTRYPOINT ["java", "-jar", "app.jar"]