package org.bszabat.mgrbackend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IOBlockingService {

    public String callsToDbLastsEndpoint(String url) {
        long startTime = System.nanoTime();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForEntity(url + "/5", String.class);
        System.out.println("Request 5s");

        restTemplate.getForEntity(url + "/4", String.class);
        System.out.println("Request 4s");

        long endTime = System.nanoTime();
        long elapsedTimeMs = (endTime - startTime) / 1000000;
        return String.format("Execution time: %ss %sms", elapsedTimeMs/1000, elapsedTimeMs%1000);
    }
}
