# Comfortly: Trip data processor microservice

## Prerequisites

```bash
docker run -d --name pg-trip-data -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=trip-data -p 5432:5432 postgres:13
docker run -d --name pg-answer-data -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=answer-data -p 5434:5432 postgres:13
docker run -d --name pg-analyzed-trip-data -e POSTGRES_USER=dbuser -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=analyzed-trip-data -p 5435:5432 postgres:13
```