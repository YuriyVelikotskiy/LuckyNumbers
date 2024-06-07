package org.hse.course.domain.application;

import org.hse.course.application.context.ContextDefaultImpl;
import org.hse.course.application.datasource.TicketRecordImpl;
import org.hse.course.domain.model.Ticket;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CounterStreamImplTest {
    @Test
    public void testCounter(){
        Supplier ticketStream = Mockito.mock(Supplier.class);
        //Mockito.when(ticketStream.counter()).thenReturn(22);

    }

    private static Stream<Ticket> eightDigitsTicketStreamSupplier() {
        var digits = 8;
        var maxNumber = (int) Math.pow(10, digits);
        Function<Integer, Stream<Ticket>> toStream =
                ((Function<Integer, IntStream>) number -> IntStream.range(0, number))
                        .andThen(IntStream::parallel)
                        .andThen(stream -> stream.mapToObj(n -> new TicketRecordImpl(n, digits)));

        return toStream.apply(maxNumber);
    }
}
