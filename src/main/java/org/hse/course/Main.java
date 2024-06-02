package org.hse.course;

import org.hse.course.datasource.TicketRecordImpl;
import org.hse.course.domain.application.CounterStreamImpl;
import org.hse.course.domain.model.Ticket;

import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        CounterStreamImpl counterStream= new CounterStreamImpl(Main::ticketStream);
        System.out.println(counterStream.counter());
    }

    private static Stream<Ticket> ticketStream(){
        int digits = 6;
        int maxNumber = (int) Math.pow(10, digits);
        Function<Integer, Stream<Ticket>> toStream =
                ((Function<Integer, IntStream>) number -> IntStream.range(0, number))
                        .andThen(IntStream::parallel)
                        .andThen(stream -> stream.mapToObj(n-> new TicketRecordImpl(n, digits)));
        return toStream.apply(maxNumber);
    }
}