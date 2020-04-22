package org.bszabat.mgrbackend.mgrtests.testcases;

import org.bszabat.mgrbackend.algorithms.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class PrimeNumbers {
    public static void main(String[] args) {
        PrimeNumbers primeNumbers = new PrimeNumbers();
        List<Double> resultsList = primeNumbers.checkIfNumbersPrimeInRangeTimes(20, 50000);
        System.out.println(resultsList);
        System.out.println(primeNumbers.countAverage(resultsList).getAsDouble());
    }

    public List<Double> checkIfNumbersPrimeInRangeTimes(int n, int number) {
        Utils utils = new Utils();
        ArrayList<Double> resultsList = new ArrayList<>();

        for(int i=0; i<n; i++) {
            String[] tab = utils.measureTime(number, org.bszabat.mgrbackend.algorithms.PrimeNumbers::checkIfNumbersPrimeInRange).split(" ");
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