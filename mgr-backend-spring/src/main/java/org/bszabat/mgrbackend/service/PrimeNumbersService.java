package org.bszabat.mgrbackend.service;

import org.bszabat.mgrbackend.algorithms.PrimeNumbers;
import org.bszabat.mgrbackend.algorithms.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrimeNumbersService {

    private Utils utils;

    @Autowired
    public PrimeNumbersService(Utils utils) {
        this.utils = utils;
    }

    public String checkIfNumbersPrimeInRangeTime(int number) {
        return utils.measureTime(number, PrimeNumbers::checkIfNumbersPrimeInRange);
    }
}
