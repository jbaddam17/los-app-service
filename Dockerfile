FROM openjdk:21-jdk
ENV ENVIRONMENT="dev"
WORKDIR /app
COPY /target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=${ENVIRONMENT}", "app.jar"]
EXPOSE 8081:8081
