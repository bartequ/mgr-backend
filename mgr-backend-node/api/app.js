require('appmetrics-dash').monitor();
const primeNumbers = require('../algorithms/primeNumbers');
const utils = require('../algorithms/utils');
const ioblockingService = require('../model/ioblockingService');
const DatabaseOperation = require('../model/databaseOperation');
const bodyParser = require('body-parser');
const express = require('express');
const { Pool, Client } = require("pg");
const app = express();
const port = 3000;


const poolPostgres = new Pool({
    user: "postgres_node",
    host: "localhost",
    database: "performance_node",
    password: "postgres_node",
    port: "5433"
});

app.use(bodyParser.json());

app.get('/api/ioblocking/photos', ioblockingService.getAllPhotos);

app.get('/api/prime-numbers/:number', (req, res) => {
    res.send(utils.measureTime(req.params.number, primeNumbers.checkIfNumbersPrimeInRange));
});

app.get('/api/database-operations/operation-lasts/:seconds', DatabaseOperation.getTimeConsumingOperation);

app.get('/api/database-operations', DatabaseOperation.readAllOperations);

//TODO add time measure
app.get('/api/database-operations/without-orm', (req, res) => {
    poolPostgres.query("SELECT * FROM db_operations", (err, dbRes) => {
        res.send(dbRes)
    });
});

app.get('/api/database-operations/:id', DatabaseOperation.readOperationById);

app.post('/api/database-operations', DatabaseOperation.createOperation);

app.put('/api/database-operations/:id', DatabaseOperation.updateDatabaseOperation);

app.delete('/api/database-operations/:id', DatabaseOperation.deleteDatabaseOperation);

app.listen(port, () => console.log(`Performance Node app listening on port ${port}`));