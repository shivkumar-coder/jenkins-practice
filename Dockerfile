FROM eclipse-temurin:17-jre as build-stage
WORKDIR /app
COPY ./mvn /app/.mvn
COPY mvnw pom.xml  /app/
COPY src /app/src
RUN chmod +x mvnw
RUN mvnw clean package 


FROM eclipse-temurin:17-jre as execution-stage
COPY --from=build-stage /app/target/*.jar /app/my-app.jar
CMD ["java","-jar", "/app/my-app.jar"]