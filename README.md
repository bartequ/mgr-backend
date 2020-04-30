## Comparison between technologies used to create web applications
### Purpose of Master Thesis is to compare performance between Spring and Node in bunch of scenarios and different types of databases.

### Prerequisites
- Docker
- Java
- Maven
- Node
- npm

### Setup mock server
See api-mock folder<br>
`URL: http://localhost:1080/api`

## Spring and Node comparison
##### 1. PrimeNumbers
The purpose of this test is to show JVM/Node computing performance. Given range is checked if within range are prime numbers.<br>
- Spring: `http://localhost:8080/api/prime-numbers?number=[RANGE_TO_CHECK]`
- Node: `http://localhost:3000/api/prime-numbers/[RANGE_TO_CHECK]`<br>
<b>Output</b> - time needed to check given range

##### 2. RAM & CPU usage
The purpose of this test is to show hardware usage during Spring/Node lifecycle.<br>
Provided by npm module appmetrics-dash 
- Spring:`http://localhost:8080`
  - `/actuator/metrics/process.cpu.usage`
  - `/actuator/metrics/jvm.memory.used`
  - `/actuator/metrics/jvm.memory.used?tag=area:heap`
  - `/actuator/metrics/jvm.memory.used?tag=area:nonheap`
- Node:`http://localhost:3001/appmetrics-dash`
<br><b>Output</b> - amount of used ram or used % of CPU

##### 3. Objects serialization
The purpose of this test is to show time needed to serialize array of objects
<br>Spring `http://localhost:8080/api/object-serialization/[string/object]/[5000/10000/40000/120000]`
<br>Node `http://localhost:3000/api/object-serialization/[string/object]/[5000/10000/40000/120000]`
<br><b>Output</b> - time needed to serialize JSON API response to String/Objects

##### 4. I/O Blocking async calls
The purpose of this test is to show time needed to perform 2 API calls simulated by local endpoints, one take 4 
seconds and second 5 seconds. Service should perform those calls as soon as possible.
<br>Spring `http://localhost:8080/api/ioblocking`
<br>Node `http://localhost:3000/api/ioblocking`
<br><b>Output</b> - time needed to perform calls

##### 5. Synchronous API calls and objects processing
The purpose of this test is to show time needed in scenario where 2 API calls have to be combined and results returned in response.
<br>API call -> API call -> merge data into objects -> Response
<br>Spring `http://localhost:8080/api/sync-api/photos/[5000/10000/40000/120000]`
<br>Node `http://localhost:3000/api/sync-api/photos/[5000/10000/40000/120000]`
<br><b>Output</b> - time needed to perform API calls and objects manipulations.

## Tests results 
`https://docs.google.com/spreadsheets/d/1Wdz5dGLTWOCbA7_xuFlbqrJBm833UJMwAyYaup49ITQ/edit?usp=sharing`