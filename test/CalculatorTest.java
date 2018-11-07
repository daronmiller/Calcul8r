import edu.bsu.calcul8r.Calculator;
import org.junit.Assert;
import org.junit.Test;

public class CalculatorTest {
    private final Calculator calculator = new Calculator();
    private final double epsilon = 0.000005;

    @Test
    public void testExponent() {
        double result = calculator.stringToAnswer("3^4=");
        Assert.assertEquals(81, result, epsilon);
    }

    @Test
    public void testAddition() {
        double result = calculator.stringToAnswer("2+25=");
        Assert.assertEquals(27, result, epsilon);
    }

    @Test
    public void testSubtraction() {
        double result = calculator.stringToAnswer("52-56=");
        Assert.assertEquals(-4, result, epsilon);
    }

    @Test
    public void testMultiplication() {
        double result = calculator.stringToAnswer("2*25=");
        Assert.assertEquals(50, result, epsilon);
    }

    @Test
    public void testDivision() {
        double result = calculator.stringToAnswer("50/2/2=");
        Assert.assertEquals(12.5, result, epsilon);
    }

    @Test
    public void testOrderOfOperations() {
        double result = calculator.stringToAnswer("5+3*2-8/4=");
        Assert.assertEquals(9, result, epsilon);
    }

    @Test
    public void testOrderOfOperationsWithDecimals() {
        double result = calculator.stringToAnswer("25.4+8.4*2-5.5/1.1=");
        Assert.assertEquals(37.2, result, epsilon);
    }

    @Test
    public void testNegativeNumberWithOrderOfOperations() {
        double result = calculator.stringToAnswer("(-)5+(-)4*2=");
        Assert.assertEquals(-13, result, epsilon);
    }

    @Test
    public void testNegativeNumber() {
        double result = calculator.stringToAnswer("(-)5+(-)4=");
        Assert.assertEquals(-9, result, epsilon);
    }
}
