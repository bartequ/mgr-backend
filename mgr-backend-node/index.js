const primeNumbers = require('./PrimeNumbers');
const express = require('express');
const app = express();
const port = 3000;

app.get('/api/prime-numbers/:number', (req, res) => {
    res.send(primeNumbers.measureTime(req.params.number, primeNumbers.checkIfNumbersPrimeInRange));
});

app.listen(port, () => console.log(`Performance Node app listening on port ${port}`));