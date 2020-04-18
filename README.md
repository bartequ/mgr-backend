# Backend in Spring and Node for purpose of Master Thesis
## Purpose of Master Thesis is to compare performance between Spring and Node in bunch of scenarios

### Setup mock server
- `docker run -d --rm -p 1080:1080 mockserver/mockserver`
- `curl -v -X PUT "http://localhost:1080/mockserver/expectation" --data "@[path_to_mock_server_expectation_file]"`
<br>In this exampleexpectation file it's just @backup.json
- `http://localhost:1080/api/photos/[number_of_objects]`

### Spring endpoints used for tests
#### Time consuming operations
- `GET /api/prime-numbers/[number_to_count]`
- `GET /api/database-operations/operation-lasts/seconds`
#### CRUD operations
- `GET /api/database-operations`
- `GET /api/database-operations/id`
- `POST /api/database-operations`
- `PUT /api/database-operations/id`
- `DELETE /api/database-operations/id`

### Spring endpoints used for tests
#### Time consuming operations
- `GET /api//prime-numbers?number=[number_to_count]`
- `GET /api/database-operations/operation-lasts/seconds`
#### CRUD operations
- `GET /api/database-operations`
- `GET /api/database-operations/id`
- `POST /api/database-operations`
- `PUT /api/database-operations/id`
- `DELETE /api/database-operations/id`