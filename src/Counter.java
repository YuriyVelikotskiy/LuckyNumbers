

import java.util.function.Function;

public class Counter {

    private final Function<Integer, Ticket> generator;

    public Counter(Function<Integer,Ticket> generator) {
        this.generator = generator;
    }

    public int counter() {
       int count =0;
        for (int i = 0; i < Math.pow(10,6); i++) {
            MayBeLucky ticket = generator.apply(i);
            if (ticket.isLucky()) count++;
        }
        return count;
    }
}
