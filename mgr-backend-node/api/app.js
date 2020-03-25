const primeNumbers = require('../algorithms/primeNumbers');
const DatabaseOperation = require('../model/databaseOperation');
const bodyParser = require('body-parser');
const express = require('express');
const app = express();
const port = 3001;

app.use(bodyParser.json());

app.get('/api/prime-numbers/:number', (req, res) => {
    res.send(primeNumbers.measureTime(req.params.number, primeNumbers.checkIfNumbersPrimeInRange));
});

app.get('/api/database-operations', DatabaseOperation.readAllOperations);

app.get('/api/database-operations/:id', DatabaseOperation.readOperationById);

app.post('/api/database-operations', DatabaseOperation.createOperation);

app.put('/api/database-operations/:id', DatabaseOperation.updateDatabaseOperation);

app.delete('/api/database-operations/:id', DatabaseOperation.deleteDatabaseOperation);

app.listen(port, () => console.log(`Performance Node app listening on port ${port}`));