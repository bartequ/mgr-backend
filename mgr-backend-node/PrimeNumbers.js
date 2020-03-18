function measureTime(number, functionToMeasure) {
    let t0 = process.hrtime();
    functionToMeasure(number);
    let t1 = process.hrtime(t0);
    return `Execution time: ${t1[0]}s ${t1[1] / 1000000}ms`
}

function checkIfNumberPrimeInRange(number) {
    let outputStr = '';

    for (let i = 2; i <= number; i++) {
        let isPrime = true;

        for (let j = 2; j < i; j++) {
            if (i % j === 0) {
                isPrime = false;
                break;
            }
        }

        if (isPrime === true) {
            outputStr += i + ' ';
        }
    }
    return outputStr;
}

module.exports = {measureTime, checkIfNumberPrimeInRange};