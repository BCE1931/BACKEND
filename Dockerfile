# ================================
# Build Stage
# ================================
FROM eclipse-temurin:17-jdk AS build

# Set working directory
WORKDIR /app

# Install dos2unix for line ending conversion
RUN apt-get update && apt-get install -y dos2unix

# Copy Maven wrapper and pom.xml first for dependency caching
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Convert mvnw to Unix format and ensure executable permission
RUN dos2unix mvnw && chmod +x mvnw

# Download project dependencies
RUN ./mvnw dependency:go-offline

# Copy the entire project source code (including mvnw again)
COPY . .

# ✅ Re-run dos2unix and chmod after final COPY
RUN dos2unix mvnw && chmod +x mvnw

# Package the Spring Boot application without running tests
RUN ./mvnw package -DskipTests

# ================================
# Run Stage
# ================================
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy the packaged jar from build stage
COPY --from=build /app/target/BACKEND-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 for Render
EXPOSE 8080

# ✅ Entry point to run your Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
