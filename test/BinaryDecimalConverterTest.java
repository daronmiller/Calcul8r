import edu.bsu.calcul8r.BinaryDecimalConverter;
import org.junit.Assert;
import org.junit.Test;

public class BinaryDecimalConverterTest {
    private final double epsilon = 0.000005;
    private final BinaryDecimalConverter converter = new BinaryDecimalConverter();

    @Test
    public void testBinaryToDecimal() {
        String answer = converter.binaryToDecimal("10000010");
        double result = Double.parseDouble(answer);
        Assert.assertEquals(130, result, epsilon);
    }

    @Test
    public void testDecimalToBinary() {
        String answer = converter.decimalToBinary("9");
        double result = Double.parseDouble(answer);
        Assert.assertEquals(1001, result, epsilon);
    }
}