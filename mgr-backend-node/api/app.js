require('appmetrics-dash').monitor();
const primeNumbers = require('../algorithms/primeNumbers');
const utils = require('../algorithms/utils');
const ioblockingService = require('../model/ioblockingService');
const DatabaseOperation = require('../model/databaseOperation');
const bodyParser = require('body-parser');
const express = require('express');
const app = express();
const port = 3000;

app.use(bodyParser.json());

app.get('/api/ioblocking/photos', ioblockingService.getAllPhotos);

app.get('/api/prime-numbers/:number', (req, res) => {
    res.send(utils.measureTime(req.params.number, primeNumbers.checkIfNumbersPrimeInRange));
});

app.get('/api/database-operations/operation-lasts/:seconds', DatabaseOperation.getTimeConsumingOperation);

app.listen(port, () => console.log(`Performance Node app listening on port ${port}`));