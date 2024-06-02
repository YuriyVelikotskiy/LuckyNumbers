package org.hse.course.datasource;

import org.hse.course.domain.model.Ticket;

public class TicketRecordIteratorImpl extends TicketIteratorImpl {
    public TicketRecordIteratorImpl(int digits) {
        super(digits);
    }

    @Override
    public Ticket getTiket() {
        return new TicketRecordImpl(getCurrent(), getDigits());
    }

}
