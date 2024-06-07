package org.hse.course.application.datasource;

import lombok.Builder;
import lombok.With;
import org.hse.course.domain.model.Ticket;

@Builder
@With
public class TicketImpl implements Ticket {
    private final int number;
    private final int digits;

    public TicketImpl(int number, int digits) {
        if (number < 0) {
            throw new IllegalArgumentException("Значение не подходит, отрицательно");
        }
        if (digits % 2 != 0) {
            throw new IllegalArgumentException("Значение не подходит, количество цифр нечетно");
        }
        if (number >= Math.pow(10, digits)) {
            throw new IllegalArgumentException("Значение не подходит, колличество цифр не подходит");
        }

        this.number = number;
        this.digits = digits;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public int getDigits() {
        return digits;
    }
}
