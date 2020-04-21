package org.bszabat.mgrbackend.mgrtests;

import org.springframework.web.client.RestTemplate;

public class IOBlocking {

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

    public static void main(String[] args) {
        System.out.println(new IOBlocking().callsToDbLastsEndpoint(URLHelper.DB_OPERATION_WAIT_FOR));
    }
}
