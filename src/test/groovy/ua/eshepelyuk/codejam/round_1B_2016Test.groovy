package ua.eshepelyuk.codejam

import spock.lang.Specification
import spock.lang.Unroll

import static ua.eshepelyuk.codejam.round_1B_2016.gettingTheDigits

class round_1B_2016Test extends Specification {
    @Unroll
    def "test gettingTheDigits"() {
        expect: ''
        gettingTheDigits(input) == output
        where: ''
        input            || output
        'OZONETOWER'      | '012'
        'WEIGHFOXTOURIST' | '2468'
        'OURNEONFOE'      | '114'
        'ETHER'           | '3'
    }
}
