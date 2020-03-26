# Backend in Spring and Node for purpose of Master Thesis
## Purpose of Master Thesis is to compare performance between Spring and Node in bunch of scenarios

### Node endpoints used for tests
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