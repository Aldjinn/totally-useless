# totally-useless

This totally useless project shows how to build an application for multiple architectures.

Please note, that a custom GitHub Actions runner running on arm64 is required. ⚠️

Unbelievable, but something like this exists at <https://github.com/myoung34/docker-github-actions-runner>.

The magic of linking Docker manifests is done by <https://github.com/Noelware/docker-manifest-action>.

☕

## Development

```bash
mvn compile quarkus:dev
```

## Build

```bash
mvn package
mvn package -Dquarkus.package.type=uber-jar
mvn package -Pnative
mvn package -Pnative -Dquarkus.native.container-build=true
```

## Dockerhub

Images can be found at <https://hub.docker.com/r/aldjinn/totally-useless>.

```bash
docker run --rm -p 8080:8080 aldjinn/totally-useless:latest
```
