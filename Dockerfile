FROM maven:3.8.6-jdk-8 AS build
COPY . .
RUN mvc clean package -DskipTests

FROM openjdk:8-jdk-slim
COPY --from=build /target/deny-shop-0.0.1-SNAPSHOT.jar deny-shop.jar
EXPOSE 8072
ENTRYPOINT ["java", "-jar", "deny-shop.jar"]