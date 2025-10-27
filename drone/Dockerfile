FROM gradle:9.1.0-jdk21 AS Builder
WORKDIR /home/gradle/src
COPY . .
RUN gradle clean build --no-daemon

FROM openjdk:21-jdk
COPY --from=Builder /home/gradle/src/build/libs/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]