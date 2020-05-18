## Postgres performance app
### Environment
##### Run Postgres container
`docker run -d -p 5432:5432 --env POSTGRES_USER=admin --env POSTGRES_PASSWORD=admin --env POSTGRES_DB=performance_postgres postgres`