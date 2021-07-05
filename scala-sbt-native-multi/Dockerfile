FROM openjdk:11-slim AS base
# Create runtime user
RUN adduser --home /app app
WORKDIR /app

# Build application
FROM base AS sbt-builder
COPY ./sbtx /app/sbtx
# To download SBT
RUN apt-get update && apt-get install -y curl
RUN ./sbtx -sbt-create exit

# Build application
FROM sbt-builder AS builder

COPY ./project /app/project
COPY ./modules /app/modules
COPY ./build.sbt /app/build.sbt

RUN ./sbtx stage

# Copy built application to base image
FROM base AS app

COPY --chown=app:app --from=builder /app/modules/worker/target/universal/stage/ /app/worker
COPY --chown=app:app --from=builder /app/modules/web/target/universal/stage/ /app/web

USER app
EXPOSE 8080
ENTRYPOINT ["/app/web/bin/scala-sbt-native-multi-web"]