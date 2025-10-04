FROM eclipse-temurin:24-jdk-alpine
VOLUME /tmp
ENV PORT 9003
EXPOSE 9003
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} course-service.jar
ENTRYPOINT ["java","-jar","/course-service.jar"]