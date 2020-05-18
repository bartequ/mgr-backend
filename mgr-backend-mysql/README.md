## Postgres performance app
### Environment
##### Run Postgres container
`docker run -d -p 3306:3306 --env MYSQL_USER=admin --env MYSQL_PASSWORD=admin --env MYSQL_DATABASE=performance_mysql --env MYSQL_ROOT_PASSWORD=admin mysql`