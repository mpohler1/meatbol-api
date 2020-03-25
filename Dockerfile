FROM openjdk:8
ENV INTERPRETER_JAR_PATH ./interpreter.jar
ENV TEMP_FILE_FOLDER_PATH /tmp/
ARG WEB_SERVER=target/*.jar
ARG INTERPRETER=interpreter.jar
COPY ${WEB_SERVER} webserver.jar
COPY ${INTERPRETER} interpreter.jar
ENTRYPOINT ["java", "-jar", "/webserver.jar"]
