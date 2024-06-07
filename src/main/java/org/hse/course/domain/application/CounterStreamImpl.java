package org.hse.course.domain.application;

import org.hse.course.domain.model.Ticket;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class CounterStreamImpl implements  Counter{
    private final Supplier<Stream<Ticket>> ticketStream;

    public CounterStreamImpl(Supplier<Stream<Ticket>> ticketStream) {
        this.ticketStream = ticketStream;
    }

    @Override
    public int counter() {
        Stream<Ticket> stream = ticketStream.get();
        return (int) stream.parallel().filter(Ticket::isLucky).count();
    }
}
