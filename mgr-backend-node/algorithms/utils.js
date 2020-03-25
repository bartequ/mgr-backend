function measureTime(number, functionToMeasure) {
    let t0 = process.hrtime();
    functionToMeasure(number);
    let t1 = process.hrtime(t0);
    return `Execution time: ${t1[0]}s ${t1[1] / 1000000}ms`
}

module.exports = {measureTime};