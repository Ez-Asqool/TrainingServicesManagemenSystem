FROM eclipse-temurin:24-jdk-alpine
VOLUME /tmp
ENV PORT 9002
EXPOSE 9002
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} trainee-service.jar
ENTRYPOINT ["java","-jar","/trainee-service.jar"] 