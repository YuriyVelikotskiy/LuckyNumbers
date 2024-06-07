package org.hse.course.domain.business;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Delegate;
import lombok.experimental.FieldDefaults;
import org.hse.course.domain.model.Ticket;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TicketEvenDecorator implements Ticket {
    private interface Exclude {
        boolean isLucky();
    }
    private interface Includes {
        int getNumber();
        int getDigits();
    }

    @Delegate(types = Includes.class)
//    @Delegate(excludes = Exclude.class)
    Ticket ticket;

    @Override
    public boolean isLucky() {
        return (ticket.getNumber() % 2 == 0) && ticket.isLucky();
    }
}