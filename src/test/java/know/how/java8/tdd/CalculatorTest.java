package know.how.java8.tdd;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * Tests for TDD KATA - String Calculator.
 */
public class CalculatorTest {
    private Calculator calculator;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testAdd_emptyStringShouldReturnZero() throws Exception {
        int result = calculator.add("");
        assertEquals(0, result);
    }

    @Test
    public void testAdd_oneNumberShouldReturnItself() throws Exception {
        int result = calculator.add("1");
        assertEquals(1, result);
    }

    @Test
    public void testAdd_twoNumbersShouldReturnSum() throws Exception {
        int result = calculator.add("1,2");
        assertEquals(3, result);
    }

    @Test
    public void testAdd_moreNumbersShouldReturnSum() throws Exception {
        int result = calculator.add("1,2,3");
        assertEquals(6, result);
    }

    @Test
    public void testAdd_newLineDelimiterIsAlsoAllowed() throws Exception {
        assertEquals(6, calculator.add("1,2\n3"));
        assertEquals(6, calculator.add("1\n2\n3"));
        assertEquals(10, calculator.add("1\n2\n3,4"));
    }

    @Test
    public void testAdd_newDelimiterShouldBeSupported() throws Exception {
        assertEquals(3, calculator.add("//;\n1;2"));
        assertEquals(3, calculator.add("//?\n1?2"));
        assertEquals(6, calculator.add("//>\n1>2\n3"));
    }

    @Test (expected = NegativesNotAllowedException.class)
    public void testAdd_negativeLastNumberShouldThrowException() throws Exception {
        calculator.add("//;\n1;2;-5");
    }

    @Test (expected = NegativesNotAllowedException.class)
    public void testAdd_negativesNumbersShouldThrowException() throws Exception {
        calculator.add("//;\n1;-2;5");
    }

    @Test (expected = NegativesNotAllowedException.class)
    public void testAdd_negativeFirstNumbersShouldThrowException() throws Exception {
        calculator.add("//;\n-1;-2;5");
    }

    @Test
    public void testAdd_negativeNumbersShouldBeIndicateInMessageOfException() throws Exception {
        exception.expect(NegativesNotAllowedException.class);
        exception.expectMessage("Negatives not allowed: -4 -3");
        calculator.add("//;\n4;6;-4;-3;2");
    }

    @Test
    public void testAdd_NumberBiggerThanThousandShouldBeIgnored() throws Exception {
        assertEquals(700, calculator.add("//;\n3;37;660;1001"));
        assertEquals(1699, calculator.add("3,37,660,999"));
        assertEquals(1700, calculator.add("3,37,660,1000"));
        assertEquals(700, calculator.add("3,37,660,2000"));
    }
}
