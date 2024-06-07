package org.hse.course.domain.business;

import org.hse.course.domain.model.Ticket;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class TicketEvenDecoratorTest {

    private final Ticket ticket = Mockito.mock(Ticket.class);

    @Before
    public void init() {
        Mockito.when(ticket.isLucky()).thenReturn(true);
        Mockito.when(ticket.getNumber()).thenReturn(100100);
    }

    @Test
    public void testEvenLuckyTicket() {

        TicketEvenDecorator decorator = new TicketEvenDecorator(ticket);

        boolean actual = decorator.isLucky();

        assertTrue("Этот билет счастливый", actual);
        Mockito.verify(ticket, Mockito.times(1)).isLucky();
        Mockito.verify(ticket, Mockito.times(1)).getNumber();
    }

    @Test
    public void testOddLuckyTicket() {

        Mockito.when(ticket.getNumber()).thenReturn(101101);
        TicketEvenDecorator decorator = new TicketEvenDecorator(ticket);

        boolean actual = decorator.isLucky();

        assertFalse("Этот билет не счастливый", actual);
        Mockito.verify(ticket, Mockito.never()).isLucky();
        Mockito.verify(ticket, Mockito.times(1)).getNumber();
    }

    @Test
    public void testEvenNotLuckyTicket() {
        Mockito.when(ticket.getNumber()).thenReturn(101100);
        Mockito.when(ticket.isLucky()).thenReturn(false);
        TicketEvenDecorator decorator = new TicketEvenDecorator(ticket);

        boolean actual = decorator.isLucky();

        assertFalse("Этот билет счастливый", actual);
        Mockito.verify(ticket, Mockito.times(1)).isLucky();
        Mockito.verify(ticket, Mockito.times(1)).getNumber();
    }
}