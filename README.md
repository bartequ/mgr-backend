# Backend in Spring and Node for purpose of Master Thesis
### Purpose of Master Thesis is to compare performance between Spring and Node in bunch of scenarios

### Prerequisites
- Docker
- Java
- Maven
- Node
- npm

### Setup mock server
See api-mock folder<br>
`URL: http://localhost:1080/api`

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

## Tests results 
`https://docs.google.com/spreadsheets/d/1Wdz5dGLTWOCbA7_xuFlbqrJBm833UJMwAyYaup49ITQ/edit?usp=sharing`