## Other tests:
- Resources usage
<br>Endpoints available for tests:
  - `/actuator/metrics/process.cpu.usage`
  - `/actuator/metrics/jvm.memory.used`
  - `/actuator/metrics/jvm.memory.used?tag=area:heap`
  - `/actuator/metrics/jvm.memory.used?tag=area:nonheap`
  
- Endpoint with I/O Blocking operations (2 API calls)
  - `http://localhost:8080/api/ioblocking/photos`