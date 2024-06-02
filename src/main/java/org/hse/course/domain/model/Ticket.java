package org.hse.course.domain.model;

public interface Ticket {
    default boolean isLucky() {
        int divisor = (int) Math.pow(10, getDigits() / 2);
        return getSumOfDigits(getNumber() / divisor) == getSumOfDigits(getNumber() % divisor);
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

