package know.how.java8.tdd;

public class NegativesNotAllowedException extends Exception {

    public NegativesNotAllowedException(String message) {
        super("Negatives not allowed:" + message);
    }
}
