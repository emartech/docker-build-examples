FROM openjdk:16 AS base
# Create runtime user
RUN adduser --home /app app
USER app

WORKDIR /app

EXPOSE 8080

# Build environment
FROM base AS gradle-builder

COPY ./gradle /app/gradle
COPY ./gradlew /app/gradlew

RUN sh gradlew -v

# Build application
FROM gradle-builder AS app-builder

COPY ./src /app/src
COPY ./build.gradle /app/build.gradle

RUN sh gradlew build -x test --no-daemon

# Copy built application to base image
FROM base AS packed

COPY --from=app-builder /app/build/libs/app.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
