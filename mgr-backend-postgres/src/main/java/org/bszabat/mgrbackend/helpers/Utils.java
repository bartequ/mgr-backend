package org.bszabat.mgrbackend.helpers;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class Utils {

    public <T, R> String measureTime(T arg, Function<T, R> functionToMeasure) {
        long startTime = System.nanoTime();
        functionToMeasure.apply(arg);
        long endTime = System.nanoTime();
        long elapsedTimeMs = (endTime - startTime) / 1000000;
        return String.format("Execution time: %ss %sms", elapsedTimeMs/1000, elapsedTimeMs%1000);
    }
}
