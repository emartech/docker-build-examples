# Starting with a build environment
FROM node:14-alpine AS build
# Taking build time arguments
ARG NPM_TOKEN
# Setting the working directory
WORKDIR /app
# Copy dependency descriptors
COPY package.json package-lock.json /app/
RUN npm config set //registry.npmjs.org/:_authToken=$NPM_TOKEN
# Install dependencies
RUN npm ci --only=production
# Cleanup
RUN rm .npmrc

# Production image
FROM node:14-alpine AS prod
# Set environtment to production
ENV NODE_ENV production
WORKDIR /app
# Install needed packages with cleanup
RUN apk add --no-cache \
    dumb-init
# Setting the runtime user
USER node
# Copy dependencies from builder with new ownership
COPY --chown=node:node --from=build /app/node_modules /app/node_modules
# Copy source from local folder
COPY --chown=node:node src /app/src
# By using dumb-init and running the script directly with node, all signal will be forwarded to your node application
ENTRYPOINT ["dumb-init", "--"]
# Optional: add default start command
CMD ["node", "/app/src/server.js"]
