package org.hse.course.application.datasource;

import org.hse.course.domain.model.Ticket;
import org.junit.Test;

import static org.junit.Assert.*;

public class TicketImplTest {
    @Test
    public void testConstructor() {

        //given
        int n = 100500;
        int digits = 6;
        Ticket ticket = null;

        //when
        ticket = new TicketImpl(n, digits);

        //then
        assertNotNull("Объект должен быть создан", ticket);
        assertEquals("Должен быть создан билет с номером " + n, n, ticket.getNumber());
        assertEquals("Должен быть создан билет с номером " + digits, digits, ticket.getDigits());
    }

    @Test
    public void testNumberNegative() {
        int n = -100500;
        int digits = 6;

        try {
            Ticket ticket = new TicketImpl(n, digits);
            fail("Должно быть исключение");
        } catch (Exception e) {
            assertEquals("Должен быть текст правильный", "Значение не подходит, отрицательно", e.getMessage());
        }
    }

    @Test
    public void testEvenDigits(){
        int n = 100500;
        int digits = 5;

        try {
            Ticket ticket = new TicketImpl(n, digits);
            fail("Должно быть исключение");
        } catch (Exception e) {
            assertEquals("Должен быть текст правильный", "Значение не подходит, количество цифр нечетно", e.getMessage());
        }
    }

    @Test
    public void testNumberLength(){
        int n = 100500;
        int digits = 4;

        try {
            Ticket ticket = new TicketImpl(n, digits);
            fail("Должно быть исключение");
        } catch (Exception e) {
            assertEquals("Должен быть текст правильный", "Значение не подходит, колличество цифр не подходит", e.getMessage());
        }
    }
    @Test
    public void testIsLucky(){
        int firstTicket = 100500;
        int secondTicket = 101101;
        int digits = 6;

        Ticket firstTicketObject = new TicketImpl(firstTicket, digits);
        Ticket secondTicketObject = new TicketImpl(secondTicket, digits);

        assertNotNull("Объект должен быть создан", firstTicketObject);
        assertFalse("Этот билет счастливый", firstTicketObject.isLucky());
        assertNotNull("Объект должен быть создан", secondTicketObject);
        assertTrue("Этот билет не счастливый", secondTicketObject.isLucky());

    }
}