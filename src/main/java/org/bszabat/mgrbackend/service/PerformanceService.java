package org.bszabat.mgrbackend.service;

import org.bszabat.mgrbackend.algorithms.PrimeNumbers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerformanceService {

    private PrimeNumbers primeNumbers;

    @Autowired
    public PerformanceService(PrimeNumbers primeNumbers) {
        this.primeNumbers = primeNumbers;
    }

    public String checkIfNumbersPrimeInRange(int number) {
        return primeNumbers.measureTime(number, PrimeNumbers::checkIfNumbersPrimeInRange);
    }
}
