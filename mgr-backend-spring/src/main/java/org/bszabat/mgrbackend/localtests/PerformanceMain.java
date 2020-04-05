package org.bszabat.mgrbackend.localtests;

import org.bszabat.mgrbackend.algorithms.PrimeNumbers;
import org.bszabat.mgrbackend.algorithms.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class PerformanceMain {
    public static void main(String[] args) throws IOException {
        PerformanceMain performanceMain = new PerformanceMain();
        //performanceMain.checkIfNumbersInRangePrimeForConsoleInput();
        List<Double> resultsList = performanceMain.checkIfNumbersPrimeInRangeTimes(20, 50000);
        System.out.println(resultsList);
        System.out.println(performanceMain.countAverage(resultsList).getAsDouble());
    }

    public void checkIfNumbersInRangePrimeForConsoleInput() {
        BufferedReader reader = new BufferedReader(new InputStreamReader((System.in)));
        try {
            System.out.println("Numbers to check: ");
            int number = Integer.parseInt(reader.readLine());
            Utils utils = new Utils();
            System.out.println(utils.measureTime(number, PrimeNumbers::checkIfNumbersPrimeInRange));
        } catch (NumberFormatException | IOException e) {
            System.out.println("Invalid Format");
        }
    }

    public List<Double> checkIfNumbersPrimeInRangeTimes(int n, int number) {
        Utils utils = new Utils();
        ArrayList<Double> resultsList = new ArrayList<>();

        for(int i=0; i<n; i++) {
            String[] tab = utils.measureTime(number, PrimeNumbers::checkIfNumbersPrimeInRange).split(" ");
            double resultInMs = Double.parseDouble(tab[2].substring(0, tab[2].length() - 1)) * 1000 + Double.parseDouble(tab[3].substring(0, tab[3].length() - 2));
            System.out.println(resultInMs);
            resultsList.add(resultInMs);
        }
        return resultsList;
    }

    public OptionalDouble countAverage(List<Double> resultsList) {
        double max = resultsList.stream().mapToDouble(a -> a).max().getAsDouble();
        double min = resultsList.stream().mapToDouble(a -> a).min().getAsDouble();
        resultsList.remove(max);
        resultsList.remove(min);
        System.out.println(resultsList);
        return resultsList.stream().mapToDouble(a -> a).average();
    }
}
