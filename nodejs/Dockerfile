# ARGs can be overridden with --build-arg
ARG ALPINE_VERSION=3.14
ARG NODE_VERSION=14

# --------- STAGE: BUILD ---------

FROM node:${NODE_VERSION}-alpine${ALPINE_VERSION} AS build
# Taking build time arguments
ARG NPM_TOKEN

WORKDIR /tmp

# Install dependencies
COPY package.json package-lock.json .npmrc /tmp/
RUN npm ci --only=production

# --------- STAGE: APP ---------

FROM node:${NODE_VERSION}-alpine${ALPINE_VERSION} AS prod
# Set environtment to production
ENV NODE_ENV production
WORKDIR /app
# Install needed packages with cleanup
# RUN apk add --no-cache \
#     my-package
# Setting the runtime user
USER node
# Copy dependencies from builder with new ownership
COPY --chown=node:node --from=build /tmp/node_modules /app/node_modules
# Copy source from local folder
COPY --chown=node:node src /app/src
# Optional: add default start command
CMD ["node", "/app/src/server.js"]
