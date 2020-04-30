const axios = require('axios');

const url = 'http://localhost:3000/api/database-operations/operation-lasts';

//simplify for example frontend app
async function callsToDbLastsEndpoint(req, res) {
    console.log('start 4s db req');
    let tt0 = process.hrtime();
    axios.get(url + '/4').then(res => {
        console.log(res.data);
        let tt1 = process.hrtime(tt0);
        console.log(`Req 4s execution time: ${tt1[0]}s ${tt1[1] / 1000000}ms`);
    });
    console.log('end 4s db req');

    console.log('start 5s db req');
    let t0 = process.hrtime();
    let response = await axios.get(url + '/5').then(res => {
        console.log(res.data);
        let t1 = process.hrtime(t0);
        return `Execution time: ${t1[0]}s ${t1[1] / 1000000}ms`;
    });
    res.send(response);
    console.log('end 5s db req');
}

module.exports = {callsToDbLastsEndpoint};