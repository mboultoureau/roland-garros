# Multi-stage Dockerfile

# BUILDING
FROM maven AS builder

# Create the build folder
RUN mkdir -p /build
WORKDIR /build

# Resolve dependencies
COPY pom.xml /build
RUN mvn -f /build/pom.xml dependency:resolve dependency:resolve-plugins

# Package the application
COPY src /build/src
RUN mvn -f /build/pom.xml package

# RUNTIME
FROM openjdk:19-jdk-alpine

EXPOSE 8080
ENV APP_HOME="/app"
ENV JAVA_OPTS=""

# Create folders and volumes
RUN mkdir $APP_HOME
RUN mkdir $APP_HOME/config
RUN mkdir $APP_HOME/log

VOLUME $APP_HOME/log
VOLUME $APP_HOME/config

WORKDIR $APP_HOME

# Copy executable war file from the builder image
COPY --from=builder /build/target/*.war app.war

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar app.war" ]