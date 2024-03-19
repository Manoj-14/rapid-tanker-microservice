FROM openjdk:17-slim
EXPOSE 8888
ARG APP_NAME="spring-cloud-config-server"
ARG APP_VERSION="0.0.1-SNAPSHOT"
ARG JAR_FILE="/target/${APP_NAME}-${APP_VERSION}.jar"

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar", "app.jar"]