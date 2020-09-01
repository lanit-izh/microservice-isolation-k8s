FROM maven:3.6.3-jdk-8

EXPOSE 8098
WORKDIR /app
COPY pom.xml .
COPY src src
RUN mvn dependency:go-offline
RUN mvn -Dmaven.test.skip=true compile package spring-boot:repackage
ENTRYPOINT mvn  spring-boot:run
