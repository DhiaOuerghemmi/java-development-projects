FROM eclipse-temurin:17-jdk-focal
ARG PROFILE=prod

WORKDIR /app
COPY target/auth-service-0.1.0.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=${PROFILE}","-jar","app.jar"]
