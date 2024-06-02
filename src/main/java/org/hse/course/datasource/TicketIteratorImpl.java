package org.hse.course.datasource;

import org.hse.course.domain.business.TicketIterator;
import org.hse.course.domain.model.Ticket;

import java.util.NoSuchElementException;

public class TicketIteratorImpl implements TicketIterator {
    private final int maxNumber;
    private final int digits;
    private int current = 0;

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
        if (!this.hasNext()){
            throw new NoSuchElementException();
        }
        Ticket ticket = getTiket();
        current++;
        return ticket;
    }

    public Ticket getTiket() {
        return new TicketImpl(current, digits);
    }

    public int getDigits() {
        return digits;
    }

    public int getCurrent() {
        return current;
    }
}
