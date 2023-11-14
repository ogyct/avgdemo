FROM arm64v8/openjdk:22-ea-21
MAINTAINER avgdima
EXPOSE 8080
ARG JAR_FILE=build/libs/*.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
