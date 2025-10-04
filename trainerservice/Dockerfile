FROM eclipse-temurin:24-jdk-alpine
VOLUME /tmp
ENV PORT 9001
EXPOSE 9001
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} trainer-service.jar
ENTRYPOINT ["java","-jar","/trainer-service.jar"]