FROM eclipse-temurin:23-jre

WORKDIR /app

COPY target/security-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]