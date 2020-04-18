const axios = require('axios');
const url = 'http://localhost:1080/api/photos/5000';

function fetchTestPhotosToObj(url) {
    let t0 = process.hrtime();
    axios.get(url).then(res => {
        res.data.map(e =>
            ({id: e.id, albumId: e.albumId, title: e.title, url: e.url, thumbnailUrl: e.thumbnailUrl}));
        let t1 = process.hrtime(t0);
        console.log(`Execution time: ${t1[0]}s ${t1[1] / 1000000}ms`);
    });
}

function fetchTestPhotosToString(url) {
    let t0 = process.hrtime();
    axios.get(url).then(res => {
        JSON.stringify(res.data);
        let t1 = process.hrtime(t0);
        console.log(`Execution time: ${t1[0]}s ${t1[1] / 1000000}ms`);
    })
}

fetchTestPhotosToObj(url);
fetchTestPhotosToString(url);