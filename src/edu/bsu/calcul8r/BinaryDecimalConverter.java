package edu.bsu.calcul8r;

@SuppressWarnings("WeakerAccess")
public class BinaryDecimalConverter {

    public String binaryToDecimal(String binary) {
        int decimalValue = Integer.parseInt(binary, 2);
        return Integer.toString(decimalValue);
    }

    public String decimalToBinary(String decimal) {
        int decimalValue = Integer.parseInt(decimal, 10);
        return Integer.toBinaryString(decimalValue);
    }
}