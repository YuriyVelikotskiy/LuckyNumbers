package org.hse.course.datasource;

import org.hse.course.domain.model.Ticket;

public record TicketRecordImpl(int number, int digits) implements Ticket {
    @Override
    public int getNumber() {
        return number;
    }
    @Override
    public int getDigits() {
        return digits;
    }
}
