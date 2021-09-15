## Building

The command that builds the Node.js Docker image:
```shell
docker build -t nodejs-app --build-arg NPM_TOKEN=1234 .
```

## Node version

To use different Node version you can pass `NODE_VERSION` build arg:
```shell
docker build -t nodejs-app --build-arg NODE_VERSION=12 --build-arg NPM_TOKEN=1234 .
```

## Alpine version

This uses the current latest Alpine (3.14). If there is a vulnerability in Alpine and it is patched in next version, the `ALPINE_VERSION` arg should be updated in `Dockerfile` or passed from command line.

To check which Node version and Alpine version combinations are available, please see the [DockerHub Node repository](https://hub.docker.com/_/node).
