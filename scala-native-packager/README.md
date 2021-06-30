# Example of building Scala docker image with [SBT Native Packager](https://sbt-native-packager.readthedocs.io/)

Base image is configured in the `build.sbt` file using the `dockerBaseImage` option. For additional configuration, see the [documentation](https://sbt-native-packager.readthedocs.io/en/stable/formats/docker.html#settings)

To build an image and publish it to the local docker daemon:

```sh
$ ./sbtx Docker/publishLocal
```

To build an image and publish it to a remote docker repository:

```sh
$ ./sbtx Docker/publish
```
