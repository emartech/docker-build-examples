## Building

The command that builds the Node.js Docker image:
```shell
docker build . -t nodejs-app --build-arg NPM_TOKEN=1234
```

## Node version

To use Node v12 you have to change the `NODE_VERSION` variable to `12` and the alpine version to `3.12` in the `Dockerfile`.
