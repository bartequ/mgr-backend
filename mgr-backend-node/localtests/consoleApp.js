const primeNumbers = require('../algorithms/primeNumbers');
const utils = require('../algorithms/utils');

const readline = require('readline').createInterface({
    input: process.stdin,
    output: process.stdout
});

readline.question(`Numbers to check: `, (num) => {
    console.log(utils.measureTime(20, primeNumbers.checkIfNumbersPrimeInRange));
    readline.close();
});