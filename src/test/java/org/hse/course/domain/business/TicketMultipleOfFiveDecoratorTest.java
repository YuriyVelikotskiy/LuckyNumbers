package org.hse.course.domain.business;

import org.hse.course.domain.model.Ticket;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TicketMultipleOfFiveDecoratorTest {

    private final Ticket ticket = Mockito.mock(Ticket.class);

    @Before
    public void init() {
        Mockito.when(ticket.isLucky()).thenReturn(true);
        Mockito.when(ticket.getNumber()).thenReturn(100100);
    }

    @Test
    public void testEvenLuckyTicket() {
        TicketMultipleOfFiveDecorator decorator = new TicketMultipleOfFiveDecorator(ticket);

        boolean actual = decorator.isLucky();

        assertTrue("Этот билет должен быть счастливым", actual);
        Mockito.verify(ticket, Mockito.times(1)).isLucky();
        Mockito.verify(ticket, Mockito.times(1)).getNumber();
    }

    @Test
    public void testOddLuckyTicket() {

        Mockito.when(ticket.getNumber()).thenReturn(101101);
        TicketEvenDecorator decorator = new TicketEvenDecorator(ticket);

        boolean actual = decorator.isLucky();

        assertFalse("", actual);
        Mockito.verify(ticket, Mockito.never()).isLucky();
        Mockito.verify(ticket, Mockito.times(1)).getNumber();
    }

    @Test
    public void testEvenNotLuckyTicket() {
        Mockito.when(ticket.getNumber()).thenReturn(101100);
        Mockito.when(ticket.isLucky()).thenReturn(false);
        TicketEvenDecorator decorator = new TicketEvenDecorator(ticket);

        boolean actual = decorator.isLucky();

        assertFalse("Этот билет должен быть не счастливый", actual);
        Mockito.verify(ticket, Mockito.times(1)).isLucky();
        Mockito.verify(ticket, Mockito.times(1)).getNumber();
    }
}
