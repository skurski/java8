package know.how.java8.files;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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
        Files.lines(path)
                .map(String::toUpperCase)
                .filter(s -> s.contains("IT"))
                .forEach(System.out::println);

        System.out.println("\nPalindrome check: ");
        List<String> palindromes = Files.lines(path)
                .flatMap(line -> Stream.of(line.split(" ")))
                .filter(word -> isPalindrome(word))
                .map(String::toLowerCase)
                .collect(Collectors.toList());
        palindromes.forEach(System.out::println);

        System.out.println("\nWrite palindrome to file: palindromes.txt");
        Files.write(Paths.get("palindromes.txt"), palindromes, Charset.defaultCharset());

        System.out.println("\nWrite something to file using more high level approach: output.txt");
        try (PrintWriter writer = new PrintWriter(
                Files.newBufferedWriter(Paths.get("output.txt"), Charset.defaultCharset()))) {

            for (int i = 0; i < 10; i++) {
                writer.printf("Number is %5.2f%n", 10 * Math.random());
            }
        }

        System.out.println("\nList all files in root folder: ");
        try (Stream<Path> paths = Files.walk(Paths.get("."))) {
            paths.forEach(System.out::println);
        }

        System.out.println("\nList all text files in root folder: ");
        try (Stream<Path> paths = Files.list(Paths.get("."))) {
            paths.filter(p -> p.toString().endsWith(".txt"))
                    .forEach(System.out::println);
        }

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
