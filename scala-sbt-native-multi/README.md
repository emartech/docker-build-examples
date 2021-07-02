# Example of building a multi project Scala docker image with SBT

We recommend to use a dockerfile instead of the docker plugins for sbt/gradle projects because the docker plugins are designed to create a separate image for each process in a multi process repository. All the processes within a single GAP application must be available on a single built image to be able to run. An other options is to split the processes to separate GAP applications.

This repository contains an example project with a shared lib and two processes (web, job):


To build an image and publish it to the local docker daemon:

```sh
$ docker build -t eu.gcr.io/ems-gap-images/<applicatioName> .
```

To build an image and publish it to the gap docker repository (as part of the build pipeline in case of prebuilt images):

```sh
$ docker push eu.gcr.io/ems-gap-images/<applicatioName>

```
