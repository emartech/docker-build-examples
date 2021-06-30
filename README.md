# Example Dockerfiles to build GAP services

In this repository we collect example Dockerfiles for different stacks following best practices.

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
