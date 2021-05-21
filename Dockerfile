FROM maven:3.6.3-jdk-11 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -DskipTests=true

FROM openjdk:13-jdk-alpine
COPY --from=build /usr/src/app/target/tims-url-shortener-0.0.1-SNAPSHOT.jar /usr/src/app/tims-url-shortener-0.0.1-SNAPSHOT.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","/usr/src/app/tims-url-shortener-0.0.1-SNAPSHOT.jar"]