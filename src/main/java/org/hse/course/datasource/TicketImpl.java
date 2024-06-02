package org.hse.course.datasource;

import org.hse.course.domain.model.Ticket;

public class TicketImpl implements Ticket {
    private final int number;
    private final int digits;

    public TicketImpl(int number, int digits) {
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
