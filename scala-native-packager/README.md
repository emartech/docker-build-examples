# Example of building Scala docker image with [SBT Native Packager](https://sbt-native-packager.readthedocs.io/)

> :warning: Since sbt-native-packager builds the docker image for you, this only works with the [prebuilt image](https://gap-docs.gservice.emarsys.net/using-prebuilt-image.html) gap feature.

Base image is configured in the `build.sbt` file using the `dockerBaseImage` option. For additional configuration, see the [documentation](https://sbt-native-packager.readthedocs.io/en/stable/formats/docker.html#settings)

To build an image and publish it to the local docker daemon:

```sh
$ ./sbtx Docker/publishLocal
```

To build an image and publish it to a remote docker repository:

```sh
$ ./sbtx Docker/publish
```

To generate the Dockerfile and assemble the jar files/scripts required to run the application in the `target/docker/stage` directory:

```sh
$ ./sbtx Docker/stage
$ docker build target/docker/stage
```
