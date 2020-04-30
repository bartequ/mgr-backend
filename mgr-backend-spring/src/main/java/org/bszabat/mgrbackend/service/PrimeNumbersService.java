package org.bszabat.mgrbackend.service;

//import org.bszabat.mgrbackend.algorithms.PrimeNumbers;
import org.bszabat.mgrbackend.helpers.Utils;
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
        return utils.measureTime(number, this::checkIfNumbersPrimeInRange);
    }

    public String checkIfNumbersPrimeInRange(int number) {
        if (number < 2) {
            return "No prime numbers in given range";
        }

        String outputStr = "";
        for (int i = 2; i <= number; i++) {
            boolean isPrime = true;

            for (int j = 2; j < i; j++) {
                if(i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                outputStr += i + " ";
            }
        }
        return outputStr.substring(0, outputStr.length()-1);
    }
}
