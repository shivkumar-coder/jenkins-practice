FROM eclipse-temurin:11 AS build-stage
WORKDIR /app
COPY /.mvn /app/.mvn
COPY mvnw pom.xml /app/
COPY src /app/src
RUN ./mvnw clean package


FROM eclipse-temurin:11-jre AS execution-stage
COPY --from=build-stage /app/target/*.jar /app/my-app.jar
CMD ["java","-jar","/app/my-app.jar"]