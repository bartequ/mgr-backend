const axios = require('axios');

async function getAllPhotos(req, res) {
    try {
        const albums = await axios.get('http://localhost:1080/api/albums/10');

        const photos = await axios.get('http://localhost:1080/api/photos/10000').then(res => {

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
        res.send(photos);
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

module.exports = {getAllPhotos};