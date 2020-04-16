## Spring performance application

### To run Postgres instance for test purposes
`docker run -d -p 5432:5432 --env POSTGRES_USER=postgres_spring --env POSTGRES_PASSWORD=postgres_spring --env POSTGRES_DB=performance_spring library/postgres`

### Lifecycle
##### Build jar file
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

### Endpoints for measure hardware usage
- /actuator/metrics/process.cpu.usage
- /actuator/metrics/jvm.memory.used
- /actuator/metrics/jvm.memory.used?tag=area:heap
- /actuator/metrics/jvm.memory.used?tag=area:nonheap