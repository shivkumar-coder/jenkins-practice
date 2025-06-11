

FROM eclipse-temurin:11-jre
COPY /target/*.jar my-app.jar
CMD ["java","-jar","my-app.jar"]