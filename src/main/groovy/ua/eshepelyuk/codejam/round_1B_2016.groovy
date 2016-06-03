package ua.eshepelyuk.codejam

import groovy.transform.CompileStatic
import groovy.transform.Immutable
import groovy.transform.ToString

/**
 * https://code.google.com/codejam/contest/11254486/dashboard#s=p0
 */

@CompileStatic
@Immutable
@ToString
class DigitAcc {
    String str
    List<Tuple2> digits
}

@Immutable
@CompileStatic
@ToString
class DigitSetting {
    String name
    String digit
    String marker
}


class round_1B_2016 {
    static String gettingTheDigits(String input) {
        def settings = [new DigitSetting("ZERO", "0", "Z"),
                        new DigitSetting("TWO", "2", "W"),
                        new DigitSetting("FOUR", "4", "U"),
                        new DigitSetting("SIX", "6", "X"),
                        new DigitSetting("EIGHT", "8", "G"),

                        new DigitSetting("ONE", "1", "O"),
                        new DigitSetting("THREE", "3", "H"),
                        new DigitSetting("FIVE", "5", "F"),
                        new DigitSetting("SEVEN", "7", "S"),

                        new DigitSetting("NINE", "9", null)
        ]

        settings.inject(new DigitAcc(input, [])) { DigitAcc acc, DigitSetting item ->
            if (item.marker && acc.str.contains(item.marker) || !item.marker) {
                def itemAcc = reduceStringByDigit(acc.str, item)
                return new DigitAcc(itemAcc.str, acc.digits + itemAcc.digits)
            } else {
                return acc
            }
        }.digits.sort(false) { a, b -> a[0] <=> b[0] }.inject("") { acc, item ->
            "$acc${item[0] * item[1]}"
        }
    }

    static DigitAcc reduceStringByDigit(String s, DigitSetting digitSetting) {
        def digitCnt = digitSetting.name.collect { s.count(it) } min()
        def resStr = digitSetting.name.inject(s) { String acc, item ->
            String retval = acc
            digitCnt.times { retval = retval - item }
            retval
        }
        new DigitAcc(resStr, [new Tuple2(digitSetting.digit, digitCnt)])
    }
}
