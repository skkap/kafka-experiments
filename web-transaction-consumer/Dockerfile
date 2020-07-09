FROM openjdk:8-jdk-alpine
COPY . /usr/src/build
WORKDIR /usr/src/build
RUN ls
RUN ./gradlew bootJar 

FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY --from=0 /usr/src/build/build/libs/web-transaction-consumer.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
