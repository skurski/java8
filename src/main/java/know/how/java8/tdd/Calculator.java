package know.how.java8.tdd;

import java.util.ArrayList;
import java.util.List;

/**
 * TDD KATA - String Calculator.
 *
 * The following is a TDD Kata- an exercise in coding, refactoring and test-first, that you should apply daily for at
 * least 15 minutes (I do 30).
 *
 * Before you start:
 *  Try not to read ahead.
 *  Do one task at a time. The trick is to learn to work incrementally.
 *  Make sure you only test for correct inputs. there is no need to test for invalid inputs for this kata
 *
 * 1) Create a simple String calculator with a method int Add(string numbers)
 *      The method can take 0, 1 or 2 numbers, and will return their sum (for an empty string it will return 0) for example
 *      “” or “1” or “1,2”
 *      Start with the simplest test case of an empty string and move to 1 and two numbers
 *      Remember to solve things as simply as possible so that you force yourself to write tests you did not think about
 *      Remember to refactor after each passing test
 * 2) Allow the Add method to handle an unknown amount of numbers
 * 3) Allow the Add method to handle new lines between numbers (instead of commas).
 *      the following input is ok:  “1\n2,3”  (will equal 6)
 *      the following input is NOT ok:  “1,\n” (not need to prove it - just clarifying)
 * 4) Support different delimiters
 *      to change a delimiter, the beginning of the string will contain a separate line that looks like this:
 *      “//[delimiter]\n[numbers…]” for example “//;\n1;2” should return three where the default delimiter is ‘;’ .
 *      the first line is optional. all existing scenarios should still be supported
 * 5) Calling Add with a negative number will throw an exception “negatives not allowed” - and the negative that was
 *      passed.if there are multiple negatives, show all of them in the exception message
 *      stop here if you are a beginner. Continue if you can finish the steps so far in less than 30 minutes.
 * 6) Numbers bigger than 1000 should be ignored, so adding 2 + 1001  = 2
 * 7) Delimiters can be of any length with the following format:  “//[delimiter]\n” for example: “//[***]\n1***2***3”
 *      should return 6
 * 8) Allow multiple delimiters like this:  “//[delim1][delim2]\n” for example “//[*][%]\n1*2%3” should return 6.
 * 9) Make sure you can also handle multiple delimiters with length longer than one char
 *
 */
public class Calculator {
    private static final char DEFAULT_DELIMITER = ',';
    private static final String DELIMITER_MARKER = "//";
    private static final int DELIMITER_POSITION = 2;
    private static final int NUMBER_START_POSITION = 4; // e.g: //;\n4;3;6;8

    private String numbers = null;
    private final List<Integer> positiveValues = new ArrayList<>();
    private final List<Integer> negativeValues = new ArrayList<>();

    public int add(String stringWithNumbers) throws NegativesNotAllowedException {
        clean();
        prepare(stringWithNumbers);

        if (numbers.equals("")) {
            return 0;
        }

        return sumValues(getNumbersFromString());
    }

    private void prepare(String stringWithNumbers) {
        this.numbers = stringWithNumbers;
    }

    private void clean() {
        positiveValues.clear();
        negativeValues.clear();
    }

    private List<Integer> getNumbersFromString() throws NegativesNotAllowedException {
        char delimiter = DEFAULT_DELIMITER;
        if (numbers.startsWith(DELIMITER_MARKER)) {
            delimiter = numbers.charAt(DELIMITER_POSITION);
            numbers = numbers.substring(NUMBER_START_POSITION);
        }

        return getNumbersFromStringBasedOnDelimiter(delimiter);
    }

    private List<Integer> getNumbersFromStringBasedOnDelimiter(char delimiter)
            throws NegativesNotAllowedException {
        char[] chars = numbers.toCharArray();

        int beginIndex = 0;
        for (int i=0; i<chars.length; i++) {
            if (chars[i] == delimiter || chars[i] == '\n') {
                Integer value = Integer.valueOf(numbers.substring(beginIndex, i));
                addNumberToCollectionBasedOnSign(value);
                beginIndex = i+1;
            }
        }

        Integer lastNumber = getLastNumberInString(beginIndex);
        addNumberToCollectionBasedOnSign(lastNumber);

        if (!negativeValues.isEmpty()) {
            throw new NegativesNotAllowedException(createMessageWithNegativesNumbers(negativeValues));
        }

        return positiveValues;
    }

    private void addNumberToCollectionBasedOnSign(Integer number) {
        if (number >= 0 && number <= 1000) {
            positiveValues.add(number);
        } else if (number < 0) {
            negativeValues.add(number);
        }
    }

    private Integer getLastNumberInString(int beginIndex) {
        Integer lastNumber;
        if (positiveValues.isEmpty() && negativeValues.isEmpty()) {
            lastNumber = Integer.valueOf(numbers);
        } else {
            lastNumber = Integer.valueOf(numbers.substring(beginIndex));
        }

        return lastNumber;
    }

    private String createMessageWithNegativesNumbers(List<Integer> negativeNumbers) {
        StringBuilder sb = new StringBuilder();
        for (Integer i : negativeNumbers) {
            sb.append(" ");
            sb.append(i);
        }

        return sb.toString();
    }

    private int sumValues(List<Integer> values) {
        int result = 0;
        for (Integer val: values) {
            result += val;
        }
        return result;
    }
}
