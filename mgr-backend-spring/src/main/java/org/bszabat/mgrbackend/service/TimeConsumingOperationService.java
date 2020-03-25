package org.bszabat.mgrbackend.service;

import org.bszabat.mgrbackend.algorithms.PrimeNumbers;
import org.bszabat.mgrbackend.algorithms.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeConsumingOperationService {

    private Utils utils;

    @Autowired
    public TimeConsumingOperationService(Utils utils) {
        this.utils = utils;
    }

    public String checkIfNumbersPrimeInRange(int number) {
        return utils.measureTime(number, PrimeNumbers::checkIfNumbersPrimeInRange);
    }
}
