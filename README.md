# totally-useless

This totally useless project shows how to build an application for multiple architectures.

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
