FROM openjdk:19-jdk-slim AS build
WORKDIR /app
COPY mvnw ./
COPY .mvn .mvn
COPY pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src ./src
RUN ./mvnw clean package -DskipTests

FROM openjdk:19-jdk-slim
WORKDIR /app
COPY --from=build /app/target/birdwatchingapp-0.0.1-SNAPSHOT.jar birdwatching.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "birdwatching.jar"]