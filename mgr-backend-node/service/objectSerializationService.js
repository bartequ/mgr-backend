const axios = require('axios');
const url = 'http://localhost:1080/api/photos/';

async function fetchTestPhotos(req, res) {
    let serializationMethod = req.params.serializeTo;
    let quantity = req.params.quantity;
    if (!'5000,10000,40000,120000'.includes(quantity) || !"string,object".includes(serializationMethod)) {
        res.status(400).send();
    }

    let t0 = process.hrtime();
    let response = await axios.get(url + quantity).then(res => {
        if (serializationMethod === 'string') {
            JSON.stringify(res.data);
        }
        else {
            res.data.map(e =>
                ({id: e.id, albumId: e.albumId, title: e.title, url: e.url, thumbnailUrl: e.thumbnailUrl}));
        }
        let t1 = process.hrtime(t0);
        return `Execution time serialization to ${serializationMethod}: ${t1[0]}s ${t1[1] / 1000000}ms`;
    });
    res.send(response);
}

module.exports = {fetchTestPhotos};