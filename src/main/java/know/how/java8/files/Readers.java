package know.how.java8.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Streams should BE CLOSE when dealing with Input/Output.
 * Streams implements AutoCloseable so can be used in try with resources.
 */
public class Readers {

    public static void main(String[] args) throws IOException {

        Path path = Paths.get("input.txt");
        System.out.println("Path for input file: " + path.toAbsolutePath());

        System.out.println("\nLine containing 'IT' check: ");
        Files.lines(path).map(String::toUpperCase)
                .filter(s -> s.contains("IT"))
                .forEach(System.out::println);

        System.out.println("\nPalindrome check: ");
        Files.lines(path)
                .flatMap(line -> Stream.of(line.split(" ")))
                .filter(word -> isPalindrome(word))
                .map(String::toLowerCase)
                .forEach(System.out::println);

        System.out.println("\nFibonacci 15 first numbers: ");
        System.out.println(Arrays.toString(fibonacci(15)));
    }

    private static boolean isPalindrome(String testString) {
        for (int i = 0, j = testString.length()-1; i <= j; i++, j--) {
            if (testString.charAt(i) != testString.charAt(j)) {
                return false;
            }
        }
        return true;
//        return testString.equalsIgnoreCase(new StringBuilder(testString).reverse().toString());
    }

    private static int[] fibonacci(int limit) {
        int [] fibo = new int[limit];
        fibo[0] = 0;
        fibo[1] = 1;
        for (int i = 2; i < limit; i++) {
            fibo[i] = fibo[i-2] + fibo[i-1];
        }
        return fibo;
    }
}
