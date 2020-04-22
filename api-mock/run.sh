docker run -d --rm -p 1080:1080 mockserver/mockserver
sleep 5
curl -v -X PUT "http://localhost:1080/mockserver/expectation" --data "@mappings/5000_objects.json"
curl -v -X PUT "http://localhost:1080/mockserver/expectation" --data "@mappings/10000_objects.json"
curl -v -X PUT "http://localhost:1080/mockserver/expectation" --data "@mappings/40000_objects.json"
curl -v -X PUT "http://localhost:1080/mockserver/expectation" --data "@mappings/10_albums.json"
