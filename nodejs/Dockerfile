# Starting with a build environment
FROM node:14-alpine as build
# Taking build time arguments
ARG NPM_TOKEN
# Setting the working directory
WORKDIR /app
# Copy dependency descriptors
COPY package.json package-lock.json .npmrc ./
# Install dependencies
RUN npm i --only=prod
# Cleanup
RUN rm .npmrc

# Production image
FROM node:14-alpine as prod

WORKDIR /app
# Install needed packages and cleanup in one RUN command
RUN apk add --update-cache \
    curl \
  && rm -rf /var/cache/apk/*
# Setting the runtime user
USER node
# Copy dependencies from builder with new ownership
COPY --chown=node:node --from=build /app /app
# Copy source from local folder
COPY --chown=node:node src ./src
# Optional: add default start command
CMD ["npm", "start"]