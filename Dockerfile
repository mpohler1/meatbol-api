FROM openjdk:8
ENV INTERPRETER_JAR_PATH ./interpreter.jar
ENV TEMP_FILE_FOLDER_PATH ./temp/
ARG WEB_SERVER=target/*.jar
COPY ${WEB_SERVER} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]