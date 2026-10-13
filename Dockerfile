# ---------- Stage 1: build the jar ----------
FROM public.ecr.aws/docker/library/maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -q dependency:go-offline
COPY src ./src
RUN mvn -q -DskipTests package

# ---------- Stage 2: run the jar ----------
FROM public.ecr.aws/docker/library/eclipse-temurin:21-jre
WORKDIR /app
RUN useradd --system --no-create-home notesapp
COPY --from=build /app/target/notes-api-0.0.1-SNAPSHOT.jar app.jar
USER notesapp
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
