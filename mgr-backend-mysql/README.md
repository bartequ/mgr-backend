## Postgres performance app
### Environment
##### Run Postgres container
`docker run -d -p 3306:3306 --env MYSQL_USER=admin --env MYSQL_PASSWORD=admin --env MYSQL_DATABASE=performance_mysql --env MYSQL_ROOT_PASSWORD=admin mysql`

##### Run mock server
`sh api-mock/run.sh`
- `http://localhost:1080/api/photos/[5000/10000/40000/120000]`
- `http://localhost:1080/api/albums/10`

### Lifecycle
#####Build jar file
`mvn package`
##### Build image
`docker build -t mgr-spring:[tag] .`

##### Run container
`docker run -d -p 8080:8080 mgr-spring:[tag]`

##### Login to docker hub
`docker login`

##### Tag image
`docker tag mgr-spring:[tag] bszabat/mgr-spring:[tag]`

##### Push image
`docker push bszabat/mgr-spring:[tag]`

### Tests
##### 1. CRUD operations time
The purpose of this test is to show CRUD time operations speed.
- `/api/postgres/create/{quantity}/{all/single}`
- `/api/postgres/read/{all/single}/{quantity}`
- `/api/postgres/update/{quantity}`
- `/api/postgres/delete/{quantity}`
<br><b>Output</b> - time needed to perform given operation
##### 2. Ability to create materialized view
##### 3. Comparison between available functions set