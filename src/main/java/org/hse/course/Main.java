package org.hse.course;

import org.hse.course.application.context.Context;
import org.hse.course.application.datasource.TicketImpl;
import org.hse.course.domain.application.Counter;


import java.util.function.Consumer;

public class Main {



    public static void main(String[] args) {
        var ticket = new TicketImpl(100500, 6);
        ticket.withNumber(100501);
        var build = TicketImpl.builder().number(100500).digits(6).build();

        Context context = Context.getInstance();
        var evaluator8 = context.getObjectByName("evaluator8", Counter.class);
        evaluator8.ifPresentOrElse(COUNT, () -> System.out.println("Объект не найден!"));

        System.out.println();

        var evaluator8Even = context.getObjectByName("evaluator8MultiOf10", Counter.class);
        evaluator8Even.ifPresentOrElse(COUNT, () -> System.out.println("Объект не найден!"));
    }

    private static final Consumer<Counter> COUNT =
            counter -> {
                for (int i = 0; i < 5; i++) {
                    long start = System.currentTimeMillis();
                    int result = counter.counter();
                    long duration = System.currentTimeMillis() - start;
                    System.out.println(String.format("Всего %d шестизначных счастливых билетов. Получено за %d мс", result, duration));
                }
            };

}
