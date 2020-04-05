const primeNumbers = require('../algorithms/primeNumbers');
const utils = require('../algorithms/utils');

function checkIfNumbersPrimeInRangeTimes(n, number) {
    let results = [];
    for (let i=0; i<n; i++) {
        let tab = utils.measureTime(number, primeNumbers.checkIfNumbersPrimeInRange).split(' ');
        let resultInMs = parseFloat(tab[2].substr(0, tab[2].length - 1)) * 1000 + parseFloat(tab[3].split('.')[0]);
        console.log(resultInMs);
        results.push(resultInMs);
    }
    return results;
}

function countAverage(results) {
    let maxOfArray = Math.max.apply(Math, results);
    let minOfArray = Math.min.apply(Math, results);

    const indexOfMax = results.indexOf(maxOfArray);
    results.splice(indexOfMax, 1);

    const indexOfMin = results.indexOf(minOfArray);
    results.splice(indexOfMin, 1);
    console.log(results);
    return results.reduce((prev, curr) => prev + curr) / results.length;
}

results = checkIfNumbersPrimeInRangeTimes(20, 50000);
console.log(results);
console.log(countAverage(results));