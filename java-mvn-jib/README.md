# Example of building Java docker image with [Jib](https://github.com/GoogleContainerTools/jib) via [Maven](https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin)

> :warning: Since jib directly emits a docker image (there is no Dockerfile), this only works with the [prebuilt image](https://gap-docs.gservice.emarsys.net/using-prebuilt-image.html) gap feature.

Base image is configured in the `pom.xml` file using the `from.image` option under the jib maven plugin configuration. For additional configuration, see the [documentation](https://github.com/GoogleContainerTools/jib/tree/master/jib-maven-plugin#extended-usage)

To build an image and publish it to the local docker daemon:

```sh
$ ./mvnw compile com.google.cloud.tools:jib-maven-plugin:3.1.1:dockerBuild -Dimage=java-maven-jib:latest
```

To build an image and publish it to a remote docker repository:

```sh
$ ./mvnw compile com.google.cloud.tools:jib-maven-plugin:3.1.1:build --Dimage=eu.gcr.io/ems-gap-images/java-maven-jib:latest
```
