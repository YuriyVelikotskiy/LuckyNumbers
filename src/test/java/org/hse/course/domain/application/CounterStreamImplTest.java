package org.hse.course.domain.application;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hse.course.domain.model.Ticket;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class CounterStreamImplTest {

    private Counter counter;
    int maxNumber;

    @Before
    public void init() {
        int digits = 4;
        maxNumber = (int) Math.pow(10, digits);
        Function<Integer, Stream<Ticket>> toStream =
                ((Function<Integer, IntStream>) number -> IntStream.range(0, number))
                        .andThen(IntStream::parallel)
                        .andThen(stream -> stream.mapToObj(n -> getByNumber(digits,digits)));

        counter = new CounterStreamImpl(()->toStream.apply(maxNumber));
    }

    private Ticket getByNumber(final int n, final int digits){
        Ticket ticket = Mockito.mock(Ticket.class);
        Mockito.when(ticket.getNumber()).thenReturn(n);
        Mockito.when(ticket.getDigits()).thenReturn(digits);
        Mockito.when(ticket.isLucky()).thenReturn(true);
        return ticket;
    }

    @Test
    public void testCounter() {
        var expected = counter.counter();

        assertEquals("", maxNumber, expected);
    }
}