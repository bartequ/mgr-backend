package org.bszabat.mgrbackend.controller;

import org.bszabat.mgrbackend.service.PrimeNumbersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PrimeNumbersController {

    private PrimeNumbersService primeNumbersService;

    @Autowired
    public PrimeNumbersController(PrimeNumbersService primeNumbersService) {
        this.primeNumbersService = primeNumbersService;
    }

    @GetMapping("/prime-numbers")
    public String checkPrimeNumbers(@RequestParam int number) {
        return primeNumbersService.checkIfNumbersPrimeInRangeTime(number);
    }
}
