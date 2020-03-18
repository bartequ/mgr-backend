const primeNumbers = require('./PrimeNumbers');

const readline = require('readline').createInterface({
    input: process.stdin,
    output: process.stdout
});

readline.question(`Numbers to check: `, (num) => {
    console.log(primeNumbers.measureTime(20, primeNumbers.checkIfNumberPrimeInRange));
    readline.close();
});