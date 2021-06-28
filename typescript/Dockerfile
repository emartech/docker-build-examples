# Starting with a build environment
FROM node:14-alpine as build
# Taking build time arguments
ARG NPM_TOKEN
# Setting the working directory
WORKDIR /app
# Copy dependency descriptors
COPY package.json package-lock.json .npmrc ./
# Install dependencies
RUN npm i
# Build
COPY src ./src
COPY  tsconfig.json ./
RUN npm run build

# Installing production dependencies only
FROM node:14-alpine as npm
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
# Copy dependencies from npm with new ownership
COPY --chown=node:node --from=npm /app /app
# Copy build output from builder with new ownership
COPY --chown=node:node --from=build /app/dist /app/dist
# Optional: add default start command
CMD ["npm", "start"]