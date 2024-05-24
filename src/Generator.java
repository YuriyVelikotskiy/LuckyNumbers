import java.util.function.Function;

public class Generator implements Function<Integer, MayBeLucky> {

    @Override
    public MayBeLucky apply(Integer integer) {
        return new Ticket(integer, 6);
    }
}
