# Webhook-City

## How to use?

- API

## What is this?

- Similar to mailtrap / mailhog. 
- For testing your webhooks. Use webhook-city in your test-environment to catch all outgoing webhooks of your system that you test. Retrieve the requests via the provided API and verify them.
- Minimal setup: all requests are captures (no buckets or endpoints need to be configured)
- All requests except the two defined in the API section.

## What is this not?

Not a mock or proxy.

## Info

- h2 console: http://localhost:8080/h2-console
- use together with file-based h2


## Build

```zsh
mvn compile jib:build
```

## TODO

- enable https? 
  - https://www.thomasvitale.com/https-spring-boot-ssl-certificate/ 
  - https://www.baeldung.com/spring-boot-https-self-signed-certificate
- configurable API path? (currently /api/v1/...)
- small UI?
- API documentation -> readme file
- github public project
- new dockerhub account?
- authentication for API endpoints?
- max amount of webhooks in DB?
- token bucket rate limiting?
- mysql support?
- timestamp when persisted
- swagger documentation for API?
