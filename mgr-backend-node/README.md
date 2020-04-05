## Node performance application

### To run Postgres instance for test purposes
`docker run -d -p 5433:5432 --env POSTGRES_USER=postgres_node --env POSTGRES_PASSWORD=postgres_node --env POSTGRES_DB=performance_node library/postgres`
### Build image
`docker build -t mgr-node:[tag] .`
### Run container
`docker run -d -p 3001:3001 mgr-node:[tag]`
### Login to docker hub
`docker login`
### Tag image
`docker tag mgr-node:[tag] bszabat/mgr-node:[tag]`
### Push image
`docker push bszabat/mgr-node:[tag]`