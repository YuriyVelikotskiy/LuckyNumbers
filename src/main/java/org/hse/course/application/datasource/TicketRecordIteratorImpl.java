package org.hse.course.application.datasource;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hse.course.domain.model.Ticket;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TicketRecordIteratorImpl extends TicketIteratorImpl {
    public TicketRecordIteratorImpl(int digits) {
        super(digits);
    }

    @Override
    public Ticket getTiket() {
        return new TicketRecordImpl(getCurrent(), getDigits());
    }

}
