import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Function<Integer,Ticket> generatorLambda = x -> new Ticket(x, 6);
        Generator generator = new Generator();

        CounterLambda counterLambda = new CounterLambda(generatorLambda);
        Counter counter = new Counter(generator);

        System.out.println(counterLambda.counter());
        System.out.println(counter.counter());
    }
}