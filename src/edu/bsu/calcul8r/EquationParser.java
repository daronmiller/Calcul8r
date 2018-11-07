package edu.bsu.calcul8r;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class EquationParser {
    private List<Double> numbers = new ArrayList<>();
    private List<Character> signs = new ArrayList<>();

    public List<Double> getNumbers() {
        return numbers;
    }

    public List<Character> getSigns() {
        return signs;
    }

    public void parseEquation(String equation) {
        String currentNumber = "";
        String updatedEquation = equation.replace("(-)", "~");
        int sign = 1;
        for (int i = 0; i < updatedEquation.length(); i++) {
            boolean isDigit = Character.isDigit(updatedEquation.charAt(i));
            boolean isDecimal = updatedEquation.charAt(i) == '.';
            boolean isNegative = updatedEquation.charAt(i) == '~';
            if (isDigit || isDecimal) {
                currentNumber += updatedEquation.charAt(i);
            } else {
                if (isNegative) {
                    currentNumber = "";
                    sign = -1;
                } else {
                    numbers.add(Double.parseDouble(currentNumber) * sign);
                    signs.add(updatedEquation.charAt(i));
                    currentNumber = "";
                    sign = 1;
                }
            }
        }
    }
}