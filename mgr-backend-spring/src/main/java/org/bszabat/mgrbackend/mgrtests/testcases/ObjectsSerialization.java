package org.bszabat.mgrbackend.mgrtests.testcases;

import org.bszabat.mgrbackend.exception.CannotFetchData;
import org.bszabat.mgrbackend.mgrtests.URLHelper;
import org.bszabat.mgrbackend.model.PhotoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class ObjectsSerialization {

    public void fetchTestPhotosToPhotoDto(String url) {
        RestTemplate restTemplate = new RestTemplate();
        long startTime = System.nanoTime();
        ResponseEntity<PhotoDto[]> response = restTemplate.getForEntity(url, PhotoDto[].class);
        long endTime = System.nanoTime();
        long elapsedTimeMs = (endTime - startTime) / 1000000;
        System.out.println(String.format("Execution time serialization to Objects: %ss %sms", elapsedTimeMs/1000, elapsedTimeMs%1000));
        if (response.getStatusCode().value() != 200) {
            throw new CannotFetchData("Cannot fetch data from " + url);
        }
    }

    public void fetchTestPhotosToString(String url) {
        RestTemplate restTemplate = new RestTemplate();
        long startTime = System.nanoTime();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        long endTime = System.nanoTime();
        long elapsedTimeMs = (endTime - startTime) / 1000000;
        System.out.println(String.format("Execution time serialization to String: %ss %sms", elapsedTimeMs/1000, elapsedTimeMs%1000));
        if (response.getStatusCode().value() != 200) {
            throw new CannotFetchData("Cannot fetch data from " + url);
        }
    }

    public static void main(String[] args) {
        ObjectsSerialization objectsSerialization = new ObjectsSerialization();
        objectsSerialization.fetchTestPhotosToString(URLHelper.PHOTOS_TEST_40000);
        objectsSerialization.fetchTestPhotosToPhotoDto(URLHelper.PHOTOS_TEST_40000);
    }
}
