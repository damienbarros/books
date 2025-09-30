# ---- build stage ----
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn -B -DskipTests dependency:go-offline

COPY src ./src
RUN mvn -B -DskipTests package spring-boot:repackage

# Debug: list produced artifacts (optional, remove after troubleshooting)
# RUN ls -la /app/target

# ---- runtime stage ----
FROM eclipse-temurin:21-jre
WORKDIR /app

# copy any jar from build target (pick the first jar and name it app.jar)
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
