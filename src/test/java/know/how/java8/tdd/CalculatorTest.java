package know.how.java8.tdd;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by pskurski on 7/19/2016.
 */
public class CalculatorTest {
    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void testAdd_emptyStringShouldReturnZero() {
        int result = calculator.add("");
        assertEquals(0, result);
    }

    @Test
    public void testAdd_oneNumberShouldReturnItself() {
        int result = calculator.add("1");
        assertEquals(1, result);
    }

    @Test
    public void testAdd_twoNumbersShouldReturnSum() {
        int result = calculator.add("1,2");
        assertEquals(3, result);
    }

    @Test
    public void testAdd_moreNumbersShouldReturnSum() {
        int result = calculator.add("1,2,3");
        assertEquals(6, result);
    }

    @Test
    public void testAdd_newLineDelimeterIsAlsoAllowed() {
        assertEquals(6, calculator.add("1,2\n3"));
        assertEquals(6, calculator.add("1\n2\n3"));
        assertEquals(10, calculator.add("1\n2\n3,4"));
    }

    @Test
    public void testAdd_newDelimeterShouldBeSupported() {

    }
}
