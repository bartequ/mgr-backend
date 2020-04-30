## Spring performance application

### Environment
### To run Postgres instance for test purposes
`docker run -d -p 5432:5432 --env POSTGRES_USER=postgres_spring --env POSTGRES_PASSWORD=postgres_spring --env POSTGRES_DB=performance_spring library/postgres`
### To run mock server
`sh api-mock/run.sh`
- `http://localhost:1080/api/photos/[5000/10000/40000/120000/200000]`
- `http://localhost:1080/api/albums/10`

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

###Tests
##### 1. PrimeNumbers
The purpose of this test is to show JVM computing performance. Given range is checked if within range are prime numbers.<br>
`http://localhost:8080/api/prime-numbers?number=[RANGE_TO_CHECK]`<br>
Output - time needed to check given range

##### 2. RAM & CPU usage
The purpose of this test is to show hardware usage during Spring lifecycle.<br>
Provided by Spring Actuator - built in monitoring solution 
- `/actuator/metrics/process.cpu.usage`
- `/actuator/metrics/jvm.memory.used`
- `/actuator/metrics/jvm.memory.used?tag=area:heap`
- `/actuator/metrics/jvm.memory.used?tag=area:nonheap`
<br>Output - amount of used ram or used % of CPU

##### 3. Objects serialization
The purpose of this test is to show time needed to serialize array of objects
<br>TODO add endpoint description
<br>Output - time needed to serialize JSON API response to String/Objects

##### 4. I/O Blocking async calls
The purpose of this test is to show time needed to perform 2 API calls simulated by local endpoints, one take 4 
seconds and second 5 seconds. Service should perform those calls as soon as possible.
<br>Add endpoint description and Spring async calls
<br>Output - time needed to perform calls

##### 5. Synchronous API calls and objects processing
The purpose of this test is to show time needed in scenario where 2 API calls have to be combined and results returned in response.
<br>API call -> API call -> merge data into objects -> Response
<br>`http://localhost:8080/api/sync-api/photos`
<br>TODO add parameter to choose objects quantity
<br>Output - time needed to perform API calls and objects manipulations.