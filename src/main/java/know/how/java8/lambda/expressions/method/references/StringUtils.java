package know.how.java8.lambda.expressions.method.references;

/**
 * Created by skurskip on 10/25/2017.
 */
public class StringUtils {

    public static String transform(String s, StringFunction f) {
        return f.applyFunction(s);
    }

    public static String makeExciting(String s) {
        return (s + "!!");
    }

    private StringUtils() {}
}
