package know.how.java8.exercises;

import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Statefull supplier for stream generator.
 * FibonacciMaker has state so it cannot be used in parallel.
 */
public class FibonacciMaker implements Supplier<Long> {

    private long previous = 0;
    private long current = 1;

    @Override
    public Long get() {
        long next = previous + current;
        previous = current;
        current = next;
        return previous;
    }

    public static void main(String[] args) {

        System.out.println("First 10 fibonacci numbers: ");
        Stream.generate(new FibonacciMaker())
                .limit(10)
                .forEach(System.out::println);
    }
}
