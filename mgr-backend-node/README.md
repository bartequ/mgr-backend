## Node performance application

## Environment
### To run Postgres instance for test purposes
`docker run -d -p 5433:5432 --env POSTGRES_USER=postgres_node --env POSTGRES_PASSWORD=postgres_node --env POSTGRES_DB=performance_node library/postgres`
### To run mock server
`sh api-mock/run.sh`
- `http://localhost:1080/api/photos/[5000/10000/40000/120000/200000]`
- `http://localhost:1080/api/albums/10`

### Lifecycle
##### Build image
`docker build -t mgr-node:[tag] .`
##### Run container
`docker run -d -p 3001:3001 mgr-node:[tag]`
##### Login to docker hub
`docker login`
##### Tag image
`docker tag mgr-node:[tag] bszabat/mgr-node:[tag]`
##### Push image
`docker push bszabat/mgr-node:[tag]`

###Tests
##### 1. PrimeNumbers
The purpose of this test is to show Node computing performance. Given range is checked if within range are prime numbers.<br>
`http://localhost:3000/api/prime-numbers/[RANGE_TO_CHECK]`<br>
<b>Output</b> - time needed to check given range

##### 2. RAM & CPU usage
The purpose of this test is to show hardware usage during Node lifecycle.<br>
Provided by npm module appmetrics-dash 
- `http://localhost:3001/appmetrics-dash`
<br><b>Output</b> - amount of used ram or used % of CPU

##### 3. Objects serialization
The purpose of this test is to show time needed to serialize array of objects
<br>`http://localhost:3000/api/object-serialization/[string/object]/[5000/10000/40000/120000]`
<br><b>Output</b> - time needed to serialize JSON API response to String/Objects

##### 4. I/O Blocking async calls
The purpose of this test is to show time needed to perform 2 API calls simulated by local endpoints, one take 4 
seconds and second 5 seconds. Service should perform those calls as soon as possible.
<br>`http://localhost:3000/api/ioblocking`
<br><b>Output</b> - time needed to perform calls

##### 5. Synchronous API calls and objects processing
The purpose of this test is to show time needed in scenario where 2 API calls have to be combined and results returned in response.
<br>API call -> API call -> merge data into objects -> Response
<br>`http://localhost:3000/api/sync-api/photos/[5000/10000/40000/120000]`
<br><b>Output</b> - time needed to perform API calls and objects manipulations.