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

## Docker images

Images are built for **linux/amd64** and **linux/arm64** on every push to `main`.

### Docker Hub

<https://hub.docker.com/r/aldjinn/totally-useless>

```bash
docker run --rm -p 8080:8080 aldjinn/totally-useless:latest
```

### GitHub Container Registry

<https://github.com/Aldjinn/totally-useless/pkgs/container/totally-useless>

```bash
docker run --rm -p 8080:8080 ghcr.io/aldjinn/totally-useless:latest
```
