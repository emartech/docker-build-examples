# --------- STAGE: BUILD ---------

FROM node:14-alpine AS build

ARG NPM_TOKEN

WORKDIR /tmp

# Install dependencies
COPY package.json package-lock.json .npmrc /tmp/
RUN npm ci --only=production

# --------- STAGE: APP ---------

FROM alpine:3.14 AS app

ENV NODE_VERSION=14

# Install Node.js, NPM, dumb-init
RUN apk add --update-cache \
      nano \
      nodejs=~${NODE_VERSION} && \
    rm -rf /var/cache/apk/*

# Create application user
ENV USER_NAME=application
ENV USER_GROUP=application

RUN addgroup \
      --system ${USER_GROUP} && \
    adduser \
      --system --no-create-home --disabled-password \
      --uid=1000 --ingroup=${USER_GROUP} ${USER_NAME}

# Setup workdir
ENV APP_DIR=/app

RUN mkdir -p ${APP_DIR} && \
    chown -R ${USER_NAME}:${USER_GROUP} ${APP_DIR}

# Setup application
COPY --chown=${USER_NAME}:${USER_GROUP} --from=build /tmp/node_modules ${APP_DIR}/node_modules/
COPY --chown=${USER_NAME}:${USER_GROUP} src/ ${APP_DIR}/src/

# Set exection details
USER ${USER_NAME}
WORKDIR ${APP_DIR}
ENV NODE_ENV production

# Start application
CMD ["node", "src/index.js"]