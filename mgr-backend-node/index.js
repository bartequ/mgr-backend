const primeNumbers = require('./PrimeNumbers');
const express = require('express');
const app = express();
const port = 3000;

app.get('/api/prime-numbers', (req, res) => {
    res.send(primeNumbers.measureTime(req.params.id, primeNumbers.checkIfNumberPrimeInRange));
});

app.listen(port, () => console.log(`Performance Node app listening on port ${port}`));