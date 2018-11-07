import edu.bsu.calcul8r.EquationParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EquationParserTest {
    private final EquationParser parser = new EquationParser();
    private String equation = "2+4*4/8=";
    private List<Double> numbers = new ArrayList<>();
    private List<Character> signs = new ArrayList<>();

    @Test
    public void testNumbersList() {
        numbers.addAll(Arrays.asList(2.0, 4.0, 4.0, 8.0));
        parser.parseEquation(equation);
        List<Double> list = parser.getNumbers();
        Assert.assertEquals(numbers, list);
    }

    @Test
    public void testSignsList() {
        signs.addAll(Arrays.asList('+', '*', '/', '='));
        parser.parseEquation(equation);
        List<Character> list = parser.getSigns();
        Assert.assertEquals(signs, list);
    }
}