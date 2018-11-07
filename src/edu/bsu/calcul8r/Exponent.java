package edu.bsu.calcul8r;

@SuppressWarnings("WeakerAccess")
public class Exponent implements BinaryOperation {
    public double operate(double a, double b) {
        return Math.pow(a, b);
    }
}
