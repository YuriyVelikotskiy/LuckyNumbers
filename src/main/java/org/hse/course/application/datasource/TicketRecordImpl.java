package org.hse.course.application.datasource;

import lombok.Getter;
import org.hse.course.domain.model.Ticket;

public record TicketRecordImpl(@Getter int number,@Getter int digits) implements Ticket {}
