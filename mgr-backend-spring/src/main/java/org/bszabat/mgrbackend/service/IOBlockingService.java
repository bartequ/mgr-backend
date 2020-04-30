package org.bszabat.mgrbackend.service;

import org.bszabat.mgrbackend.helpers.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IOBlockingService {

    private Utils utils;

    @Autowired
    public IOBlockingService(Utils utils) {
        this.utils = utils;
    }

    public String callsToDbLastsEndpointTime(String url) {
        return utils.measureTime(url, this::callsToDbLastsEndpoint);
    }

    private String callsToDbLastsEndpoint(String url) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForEntity(url + "/5", String.class);
        System.out.println("Request 5s completed");

        restTemplate.getForEntity(url + "/4", String.class);
        System.out.println("Request 4s completed");

        return "API calls successful";
    }
}
