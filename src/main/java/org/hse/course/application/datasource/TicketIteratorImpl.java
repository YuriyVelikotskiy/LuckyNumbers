package org.hse.course.application.datasource;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.hse.course.domain.business.TicketIterator;
import org.hse.course.domain.model.Ticket;

import java.util.NoSuchElementException;

@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TicketIteratorImpl implements TicketIterator {
    final int maxNumber;

    @Getter(AccessLevel.PROTECTED)
    final int digits;

    @Getter(AccessLevel.PROTECTED)
    int current = 0;

    public TicketIteratorImpl(int digits) {
        this.maxNumber = (int) Math.pow(10, digits);
        this.digits = digits;
    }

    @Override
    public boolean hasNext() {
        return current < maxNumber;
    }

    @Override
    public Ticket next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        Ticket ticket = getTiket();
        current++;
        return ticket;
    }

    public Ticket getTiket() {
        return new TicketImpl(current, digits);
    }
}
