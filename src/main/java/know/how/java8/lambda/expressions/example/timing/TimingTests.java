package know.how.java8.lambda.expressions.example.timing;

import java.util.Arrays;
import java.util.Random;

/**
 * Test the functional approach for timing.
 */
public class TimingTests {

    public static void main(String[] args) {

        final int SIZE = 1_000_000;
        int [] arrayToSort = new int[SIZE];
        Random random = new Random();

        for (int i = 0; i < SIZE; i++) {
            arrayToSort[i] = random.nextInt();
        }

//        Operation.measure(() -> Arrays.sort(arrayToSort));
//        Operation.measure(() -> Arrays.parallelSort(arrayToSort));

        // combining
        Operation sortArray = () -> Arrays.sort(arrayToSort);
        Operation wasteTime = () -> {
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Operation combined = sortArray.combinedOperation(wasteTime);
        Operation.measure(combined);
    }
}
