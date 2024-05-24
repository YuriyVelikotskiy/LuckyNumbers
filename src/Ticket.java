public class Ticket implements MayBeLucky {
    public int number;
    private final int divisor;

    public Ticket(int number, int digits) {
        this.number = number;
        this.divisor = (int) Math.pow(10, (double) digits /2);
    }

    @Override
    public boolean isLucky() {
        return sumOfDigits(number / divisor) == sumOfDigits(number % divisor);
    }

    private int sumOfDigits(int number) {
        int result = 0;
        while (number > 0) {
            result += number % 10;
            number /= 10;
        }
        return result;
    }
}
