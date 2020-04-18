package org.bszabat.mgrbackend.api;

import org.bszabat.mgrbackend.exception.CannotFetchData;
import org.bszabat.mgrbackend.model.PhotoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class FetchData {

    public void fetchTestPhotos(String url) {
        RestTemplate restTemplate = new RestTemplate();
        long startTime = System.nanoTime();
        ResponseEntity<PhotoDto[]> response = restTemplate.getForEntity(url, PhotoDto[].class);
        //ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        long endTime = System.nanoTime();
        long elapsedTimeMs = (endTime - startTime) / 1000000;
        System.out.println(String.format("Execution time: %ss %sms", elapsedTimeMs/1000, elapsedTimeMs%1000));
        if (response.getStatusCode().value() != 200) {
            throw new CannotFetchData("Cannot fetch data from " + url);
        }
    }

    public static void main(String[] args) {
        new FetchData().fetchTestPhotos(URLHelper.PHOTOS_TEST_5000);
    }
}
