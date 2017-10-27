package know.how.java8.lambda.expressions.example.timing;

/**
 * Operation that can be passed to Timing Utility as a function.
 */
@FunctionalInterface
public interface Operation {

    void run();

    static void measure(Operation operation) {
        double ONE_BILION = 1_000_000_000;
        long startTime = System.nanoTime();
        operation.run();
        long endTime = System.nanoTime();
        double elapsedSeconds = (endTime - startTime) / ONE_BILION;
        System.out.printf("TIMING:    Elapsed time: %.3f seconds. %n", elapsedSeconds);
    }

    default Operation combinedOperation(Operation secondOp) {
        return () -> { run(); secondOp.run(); };
    }
}
