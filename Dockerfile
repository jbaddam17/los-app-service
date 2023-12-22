FROM openjdk:21-jdk
ENV ENVIRONMENT="dev"
WORKDIR /app
COPY /target/*.jar app.jar
ENTRYPOINT java -jar app.jar --spring.profiles.active=$ENVIRONMENT
EXPOSE 8081:8081
