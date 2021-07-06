## Building Docker

The command that builds the Node.js Docker image:
```shell
docker build . -t nodejs-app --build-arg NPM_TOKEN=1234 --secret id=npmrc,src=.npmrc
```
*Note:* Secrets are a new feature in Docker and if you’re using an older version, you might need to enable it Buildkit as follows:

```shell
DOCKER_BUILDKIT=1 docker build . -t nodejs-app --build-arg NPM_TOKEN=1234 --secret id=npmrc,src=.npmrc
```
