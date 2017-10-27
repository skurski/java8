package know.how.java8.lambda.expressions.functions.predicate;

import java.util.List;
import java.util.function.Predicate;

/**
 * Uses Predicate for finding objects.
 */
public class Matcher {

    public static <T> T firstMatch(List<T> candidates, Predicate<T> matchFunction) {
        for (T candidate : candidates) {
            if (matchFunction.test(candidate)) {
                System.out.println("Matcher found: " + candidate);
                return candidate;
            }
        }

        return null;
    }

    private Matcher() {}
}
