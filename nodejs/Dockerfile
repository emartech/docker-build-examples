# Starting with a build environment
FROM node:14-alpine AS build
# Taking build time arguments
ARG NPM_TOKEN
# Setting the working directory
WORKDIR /app
# Copy dependency descriptors
COPY package.json package-lock.json /app/
# Install dependencies
RUN --mount=type=secret,mode=0644,id=npmrc,target=/app/.npmrc npm ci --only=production

# Production image
FROM node:14-alpine AS prod
# Set environtment to production
ENV NODE_ENV production
WORKDIR /app
# Install needed packages and cleanup in one RUN command
RUN apk add --update-cache \
    curl \
    dumb-init \
  && rm -rf /var/cache/apk/*
# Setting the runtime user
USER node
# Copy dependencies from builder with new ownership
COPY --chown=node:node --from=build /app/node_modules /app/node_modules
# Copy source from local folder
COPY --chown=node:node src /app/src
# By using dumb-init and running the script directly with node, all signal will be forwarded to your node application
# Optional: add default start command
ENTRYPOINT ["dumb-init", "--"]
CMD ["node", "/app/src/server.js"]
