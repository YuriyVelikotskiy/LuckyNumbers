package org.hse.course.domain.business;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TicketUtils {
    public int getDigitsSum(int number) {
        var result = 0;
        for (var tmpNumber = number; tmpNumber > 0; tmpNumber /= 10) {
            int currentDigit = tmpNumber % 10;
            result += currentDigit;
        }
        return result;
    }
}
