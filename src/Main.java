import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Function<Integer,Ticket> generator = x -> new Ticket(x, 6);
        Counter counter = new Counter(generator);
        System.out.println(counter.counter());
    }
}