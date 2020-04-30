//package org.bszabat.mgrbackend.algorithms;
//
//import org.springframework.stereotype.Component;
//
//@Component
//public class PrimeNumbers {
//
//    public static String checkIfNumbersPrimeInRange(int number) {
//        if (number < 2) {
//            return "No prime numbers in given range";
//        }
//
//        String outputStr = "";
//        for (int i = 2; i <= number; i++) {
//            boolean isPrime = true;
//
//            for (int j = 2; j < i; j++) {
//                if(i % j == 0) {
//                    isPrime = false;
//                    break;
//                }
//            }
//            if (isPrime) {
//                outputStr += i + " ";
//            }
//        }
//        return outputStr.substring(0, outputStr.length()-1);
//    }
//
//    public static void main(String[] args) {
//        long startTime = System.nanoTime();
//        PrimeNumbers.checkIfNumbersPrimeInRange(300000);
//        long endTime = System.nanoTime();
//        long elapsedTimeMs = (endTime - startTime) / 1000000;
//        System.out.println(String.format("Execution time: %ss %sms", elapsedTimeMs/1000, elapsedTimeMs%1000));
//    }
//}
