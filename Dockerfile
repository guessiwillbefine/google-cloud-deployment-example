FROM maven:3.8.4 AS build
ENV HOME=/app
RUN mkdir -p "$HOME"
WORKDIR $HOME
COPY pom.xml $HOME
RUN mvn verify --fail-never
COPY . $HOME
RUN mvn clean package

FROM eclipse-temurin:17-alpine
COPY --from=build /app/target/google-cloud-cicd-example-0.0.1.jar /app/runner.jar
ENTRYPOINT ["java", "-jar", "/app/runner.jar"]
