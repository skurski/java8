package know.how.java8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Stream exercises.
 */
public class Exercises {

    private static List<String> words = Arrays.asList("hi", "hello", "good", "bye", "what", "why", "is", "it", "when",
            "after", "before", "gone", "which", "that", "then", "and", "hihi", "goodbye");

    public static void main(String[] args) {
       String result = words.stream()
               .map(String::toUpperCase)
               .filter(s -> s.length() < 4)
               .filter(s -> s.contains("E"))
               .findFirst()
               .orElse("No word");
        System.out.println(result);

        String result2 = words.stream()
                .filter(s -> s.length() < 4)
                .filter(s -> s.contains("q"))
                .map(s -> s.toUpperCase())
                .findFirst()
                .orElse("No word");
        System.out.println(result2);


        /** Check lazy evaluation */
        Predicate<String> lessThan5chars = s -> {
            System.out.println("Checking if less than 4 characters!");
            return s.length() < 5;
        };
        Predicate<String> containsE = s -> {
            System.out.println("Contains E!");
            return s.contains("e");
        };
        Function<String, String> toUpperCaseFunction = s -> {
            System.out.println("To upper case!");
            return s.toUpperCase();
        };

        System.out.println("--- LAZY EVALUATION --- 1 EXERCISE ------------");
        String res = words.stream()
                .filter(lessThan5chars)
                .filter(containsE)
                .map(toUpperCaseFunction)
                .findFirst()
                .orElse("No word");
        System.out.println(res);

        System.out.println("--- LAZY EVALUATION --- 2 EXERCISE ------------");
        String[] wordList = words.stream()
                .filter(lessThan5chars)
                .filter(containsE)
                .map(toUpperCaseFunction)
                .toArray(String[]::new);
        System.out.println(Arrays.toString(wordList));

    }
}
