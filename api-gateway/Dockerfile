FROM bellsoft/liberica-openjdk-alpine:17.0.5
WORKDIR /app
COPY target/app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
