# totally-useless 🤪

This totally useless project shows how to build an application for multiple architectures. 

Please note, that a custom GitHub Actions runner running on arm64 is required. ⚠️ 

Unbelievable, but something like this exists at https://github.com/myoung34/docker-github-actions-runner.

The magic of linking Docker manifests is done by https://github.com/Noelware/docker-manifest-action.

☕

## Development

```bash
./mvnw compile quarkus:dev
```

## Build

```bash
./mvnw package
./mvnw package -Dquarkus.package.type=uber-jar
./mvnw package -Pnative
./mvnw package -Pnative -Dquarkus.native.container-build=true
```
