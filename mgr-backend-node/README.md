## Node performance application

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