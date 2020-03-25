package org.bszabat.mgrbackend.algorithms;

import org.springframework.stereotype.Component;

@Component
public class PrimeNumbers {

    public static String checkIfNumbersPrimeInRange(int number) {
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
        return outputStr;
    }
}
