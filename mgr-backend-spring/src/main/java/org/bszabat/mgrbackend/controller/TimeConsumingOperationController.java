package org.bszabat.mgrbackend.controller;

import org.bszabat.mgrbackend.service.TimeConsumingOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TimeConsumingOperationController {

    private TimeConsumingOperationService timeConsumingOperationService;

    @Autowired
    public TimeConsumingOperationController(TimeConsumingOperationService timeConsumingOperationService) {
        this.timeConsumingOperationService = timeConsumingOperationService;
    }

    @GetMapping("/prime-numbers")
    public String checkPrimeNumbers(@RequestParam int number) {
        return timeConsumingOperationService.checkIfNumbersPrimeInRange(number);
    }
}
