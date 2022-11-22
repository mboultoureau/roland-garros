FROM maven

RUN mkdir -p /app
WORKDIR /app

COPY pom.xml /app
RUN mvn -f /app/pom.xml dependency:resolve dependency:resolve-plugins

EXPOSE 80