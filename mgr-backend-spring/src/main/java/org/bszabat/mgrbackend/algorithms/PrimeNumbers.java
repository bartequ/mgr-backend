package org.bszabat.mgrbackend.algorithms;

import org.springframework.stereotype.Component;

import java.util.function.Function;

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

    public String measureTime(int number, Function<Integer, String> functionToMeasure) {
        long startTime = System.nanoTime();
        functionToMeasure.apply(number);
        long endTime = System.nanoTime();
        long elapsedTimeMs = (endTime - startTime) / 1000000;
        return String.format("Execution time: %ss %sms", elapsedTimeMs/1000, elapsedTimeMs%1000);
    }
}
