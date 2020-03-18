package org.bszabat.mgrbackend;

import org.bszabat.mgrbackend.algorithms.PrimeNumbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PerformanceMain {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader((System.in)));
        try {
            System.out.println("Numbers to check: ");
            int number = Integer.parseInt(reader.readLine());
            PrimeNumbers checkPrimes = new PrimeNumbers();
            System.out.println(checkPrimes.measureTime(number, PrimeNumbers::checkIfNumbersPrimeInRange));
        } catch (NumberFormatException e) {
            System.out.println("Invalid Format");
        }
    }
}
