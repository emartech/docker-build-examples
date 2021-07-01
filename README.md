# Example Dockerfiles to build GAP services

In this repository we collect example Dockerfiles for different stacks following best practices.

### GAP build types

We have three build types for GAP:
* ~~buildpack~~ (deprecated): you don't have to provide build instructions, GAP will figure out your stack and build the image with the help of heroku buildpacks.
* docker: you provide a `Dockerfile` in your repository root and GAP will build your image in the CloudBuild step.
* pre-built image: you build and push your image to `eu.gcr.io` however you want and GAP will use that image.

## Best practices

- Always use either [Docker Official Images](https://docs.docker.com/docker-hub/official_images/) or images from Verified Publishers (you can filter for such images on Docker Hub).
- If you need additional packages try to be minimal (ex. use `--no-install-recommends` flag) and clean up package manager cache in the same `RUN` command (ex. `apt-get clean`).
- Use multi-stage builds when it makes sense: install dependencies and build/compile in one stage and copy assets needed for production into the final stage only.
- Research showed that usually `alpine` is best and `ubuntu` is quite good as well, however ex. `debian:stretch-slim` is way worse. You can always use the built-in scanning tool to verify base images, ex. `docker scan ubuntu:bionic`.

## Example stacks

- [NodeJS backend](nodejs/)
- [TypeScript](typescript/)
- [Java SpringBoot with Gradle](java-gradle/)
- [Java SpringBoot with Gradle and Jib](java-gradle-jib/)
- [Java SpringBoot with Maven and Jib](java-mvn-jib/)
- [PHP Apache](php-apache/)
- [PHP FPM NGINX](php-fpm-nginx/)
- [Scala with SBT Native Packager](scala-native-packager/)