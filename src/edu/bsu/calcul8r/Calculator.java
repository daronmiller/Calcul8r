package edu.bsu.calcul8r;

import java.util.*;

public class Calculator {
    private EquationParser equationParser = new EquationParser();
    private List<Character> signList = new ArrayList<>();
    private Map<Character, BinaryOperation> map = new HashMap<>();

    private void setMap() {
        map.put('^', new Exponent());
        map.put('*', new Multiplication());
        map.put('/', new Division());
        map.put('+', new Addition());
        map.put('-', new Subtraction());
    }

    public double stringToAnswer(String equation) {
        equationParser.parseEquation(equation);
        List<Double> numbers = equationParser.getNumbers();
        List<Character> signs = equationParser.getSigns();
        return calculate(numbers, signs);
    }

    private double calculate(List<Double> numbers, List<Character> signs) {
        signList.addAll(Arrays.asList('^', '*', '/', '+', '-'));
        while (signs.size() > 1) {
            for (int i = 0; i < 5; i++) {
                performOperation(signList.get(i), numbers, signs);
            }
        }
        double finalAnswer = numbers.get(0);
        numbers.clear();
        signs.clear();
        return finalAnswer;
    }

    private void performOperation(char c, List<Double> numbers, List<Character> signs) {
        setMap();
        BinaryOperation operation = map.get(c);
        for (int i = 0; i < signs.size(); i++) {
            if (signs.get(i) == c) {
                double result = operation.operate(numbers.get(i), numbers.get(i + 1));
                numbers.remove(i + 1);
                numbers.set(i, result);
                signs.remove(i);
            }
        }
    }
}