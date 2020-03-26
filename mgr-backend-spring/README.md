## Spring performance application

### Build jar file
`mvn package`
### Build image
`docker build -t mgr-spring:[tag] .`
### Run container
`docker run -d -p 8080:8080 mgr-spring:[tag]`
### Login to docker hub
`docker login`
### Tag image
`docker tag mgr-spring:[tag] bszabat/mgr-spring:[tag]`
### Push image
`docker push bszabat/mgr-spring:[tag]`