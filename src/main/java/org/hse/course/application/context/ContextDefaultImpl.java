package org.hse.course.application.context;

import org.hse.course.application.datasource.TicketImpl;
import org.hse.course.application.datasource.TicketIteratorImpl;
import org.hse.course.application.datasource.TicketRecordImpl;
import org.hse.course.domain.application.Counter;
import org.hse.course.domain.application.CounterImpl;
import org.hse.course.domain.application.CounterStreamImpl;
import org.hse.course.domain.business.TicketEvenDecorator;
import org.hse.course.domain.business.TicketMultipleOfFiveDecorator;
import org.hse.course.domain.model.Ticket;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ContextDefaultImpl implements Context {
    private final Map<String, Counter> context;

    public ContextDefaultImpl() {
        this.context = new HashMap<>();
        var evaluator8 = new CounterStreamImpl(ContextDefaultImpl::eightDigitsTicketStreamSupplier);
        context.put("evaluator8", evaluator8);

        BiFunction<Integer, Integer, Ticket> toTicketDecoratorEven = (n, digits) -> new TicketEvenDecorator(new TicketRecordImpl(n, digits));
        var evaluator8Even = new CounterStreamImpl(eightDigitsTicketStreamSupplier(toTicketDecoratorEven));
        context.put("evaluator8Even", evaluator8Even);


        BiFunction<Integer, Integer, Ticket> toTicketMultipleOfFiveDecorator = (n, digits) -> new TicketMultipleOfFiveDecorator(new TicketRecordImpl(n, digits));
        var evaluator8Multi = new CounterStreamImpl(eightDigitsTicketStreamSupplier(toTicketMultipleOfFiveDecorator));
        context.put("evaluator8Multi", evaluator8Multi);

        var evaluator8MultiOf10 =
        new CounterStreamImpl(eightDigitsTicketStreamSupplier((n, digits)-> new TicketMultipleOfFiveDecorator(new TicketEvenDecorator(new TicketImpl(n,digits)))));
        context.put("evaluator8MultiOf10", evaluator8MultiOf10);

        var evaluator6= new CounterImpl(() -> new TicketIteratorImpl(6));
        context.put("evaluator6", evaluator6);
    }

    @Override
    public <T> Optional<T> getObjectByName(String name, Class<T> clazz) {
        var object = context.get(name);
        if (object == null) {
            return Optional.empty();
        }
        if (clazz.isAssignableFrom(object.getClass())) {
            return Optional.of((T) object);
        }

        return Optional.empty();
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

    private static Supplier<Stream<Ticket>> eightDigitsTicketStreamSupplier(final BiFunction<Integer, Integer, Ticket> toTicket) {
        var digits = 8;
        var maxNumber = (int) Math.pow(10, digits);
        Function<Integer, Stream<Ticket>> toStream =
                ((Function<Integer, IntStream>) number -> IntStream.range(0, number))
                        .andThen(IntStream::parallel)
                        .andThen(stream -> stream.mapToObj(n -> toTicket.apply(n, digits)));

        return () -> toStream.apply(maxNumber);
    }


}
