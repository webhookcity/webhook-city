# Webhook-City

## How to use?

- API

## Info

- h2 console: http://localhost:8080/h2-console
- use together with file-based h2


## Build

```zsh
mvn compile com.google.cloud.tools:jib-maven-plugin:3.1.4:build \
    -Djib.from.image="amazoncorretto:17.0.1-alpine" \
    -Dimage=bayerlsebastian/webhook-city:0.0.4
```


## TODO

- enable https? 
  - https://www.thomasvitale.com/https-spring-boot-ssl-certificate/ 
  - https://www.baeldung.com/spring-boot-https-self-signed-certificate
- configurable API path? (currently /api/v1/...)
- small UI?
- API documentation -> readme file
- latest docker image
- github repo
- integrate jib as a plugin