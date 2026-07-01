FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

RUN apk add --no-cache maven

COPY pom.xml .
COPY src ./src

RUN mvn -DskipTests clean package

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=0 /app/target/*.jar app.jar

EXPOSE 8102

ENTRYPOINT ["java","-jar","app.jar"]