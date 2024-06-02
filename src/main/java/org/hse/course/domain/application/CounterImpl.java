package org.hse.course.domain.application;

import org.hse.course.domain.business.TicketIterator;
import org.hse.course.domain.model.Ticket;

import java.util.function.Supplier;

public class CounterImpl implements Counter {

    private final Supplier<TicketIterator> ticketIterator;

    public CounterImpl(Supplier<TicketIterator> ticketIterator) {
        this.ticketIterator = ticketIterator;
    }

    public int counter() {
        TicketIterator iterator = ticketIterator.get();

        int count =0;

        while (iterator.hasNext()) {
            Ticket ticket = iterator.next();
            if (ticket.isLucky()) count++;
        }
        return count;
    }
}
