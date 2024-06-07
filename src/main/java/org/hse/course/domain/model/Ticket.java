package org.hse.course.domain.model;

import org.hse.course.domain.business.TicketUtils;

public interface Ticket {
    default boolean isLucky() {
        int divisor = (int) Math.pow(10, getDigits() / 2);
        return TicketUtils.getDigitsSum(getNumber() / divisor) == TicketUtils.getDigitsSum(getNumber() % divisor);
    }


    private int getSumOfDigits(int number) {
        int result = 0;
        while (number > 0) {
            result += number % 10;
            number /= 10;
        }
        return result;
    }

    int getNumber();

    int getDigits();
}

