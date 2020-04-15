package org.bszabat.mgrbackend.algorithms

import spock.lang.Specification

class PrimeNumbersSpec extends Specification {

    def "check if returned prime numbers are correct"() {
        given:
            def correctNumberOutput = "2 3 5 7 11 13 17 19 23 29"

        when:
            def methodOutput = PrimeNumbers.checkIfNumbersPrimeInRange(30)

        then:
            correctNumberOutput.equals(methodOutput)
    }

    def "number less then 2 is passed as range to check prime numbers"() {
        given:
            def correctOutput = "No prime numbers in given range"

        when:
            def methodOutput = PrimeNumbers.checkIfNumbersPrimeInRange(1)

        then:
        correctOutput.equals(methodOutput)
    }
}
