package org.bszabat.controller;

import org.bszabat.service.PerformanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PerformanceController {

    private PerformanceService performanceService;

    @Autowired
    public PerformanceController(PerformanceService performanceService) {
        this.performanceService = performanceService;
    }

    @GetMapping(path = "/prime-numbers")
    public String checkPrimeNumbers(@RequestParam int number) {
        return performanceService.checkIfNumbersPrimeInRange(number);
    }
}
