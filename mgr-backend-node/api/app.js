require('appmetrics-dash').monitor();
const primeNumbers = require('../service/primeNumbersService');
const utils = require('../algorithms/utils');
const syncApiService = require('../service/syncApiService');
const DatabaseOperation = require('../service/databaseOperation');
const objectSerializationService = require('../service/objectSerializationService');
const ioBlockingService = require('../service/ioBlockingService');
const bodyParser = require('body-parser');
const express = require('express');
const app = express();
const port = 3000;

app.use(bodyParser.json());

app.get('/api/prime-numbers/:number', (req, res) => {
    res.send(utils.measureTime(req.params.number, primeNumbers.checkIfNumbersPrimeInRange));
});

app.get('/api/object-serialization/:serializeTo/:quantity', objectSerializationService.fetchTestPhotos);

app.get('/api/ioblocking', ioBlockingService.callsToDbLastsEndpoint);

app.get('/api/sync-api/photos/:quantity', syncApiService.getAllPhotos);

app.get('/api/database-operations/operation-lasts/:seconds', DatabaseOperation.getTimeConsumingOperation);

app.listen(port, () => console.log(`Performance Node app listening on port ${port}`));