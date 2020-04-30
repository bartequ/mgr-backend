const axios = require('axios');
const url = 'http://localhost:1080/api/photos/';

async function getAllPhotosTime(req, res) {
    try {
        let quantity = req.params.quantity;
        if (!'5000,10000,40000,120000'.includes(quantity)) {
            res.status(400).send();
        }

        let t0 = process.hrtime();
        const albums = await axios.get('http://localhost:1080/api/albums/10');
        await axios.get(url + quantity).then(res => {

            return res.data.map(p => ({
                id: p.id,
                albumId: p.albumId,
                title: p.title,
                url: p.url,
                thumbnailUrl: p.thumbnailUrl,
                description: albums.data.filter(a => {
                    return a.albumId === getAlbumIdAndReduceWithinRange(p.albumId, 10);
                }).map(a => {
                    return a.description;
                })
            }))
        });
        let t1 = process.hrtime(t0);
        res.send(`Execution time serialization to objects: ${t1[0]}s ${t1[1] / 1000000}ms`);
    } catch (e) {
        res.status(500).send(e);
    }
}

function getAlbumIdAndReduceWithinRange(param, range) {
    if (param > 99) {
        return 1;
    }
    else if (param <= range) {
        return param;
    }
    let paramStr = param.toString();
    return parseInt(paramStr.substr(paramStr.length - 2, paramStr.length - 1),10);
}

module.exports = {getAllPhotos: getAllPhotosTime};