package org.bszabat.mgrbackend.algorithms;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class Utils {

    public String measureTime(int number, Function<Integer, String> functionToMeasure) {
        long startTime = System.nanoTime();
        functionToMeasure.apply(number);
        long endTime = System.nanoTime();
        long elapsedTimeMs = (endTime - startTime) / 1000000;
        return String.format("Execution time: %ss %sms", elapsedTimeMs/1000, elapsedTimeMs%1000);
    }
}
