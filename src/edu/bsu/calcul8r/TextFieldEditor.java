package edu.bsu.calcul8r;

import javafx.scene.control.TextField;

@SuppressWarnings("WeakerAccess")
public class TextFieldEditor {

    public void performBackspaceOperation(TextField textField) {
        textField.requestFocus();
        String textFieldText = textField.getText();
        int length = textField.getLength();
        if (length > 0) {
            textFieldText = textFieldText.substring(0, length - 1);
            textField.setText(textFieldText);
            textField.positionCaret(length);
        }
    }

    public void updateTextField(TextField textField, String symbol) {
        textField.requestFocus();
        String textFieldText = textField.getText();
        String enteredNumber = textFieldText + symbol;
        textField.setText(enteredNumber);
        int length = textField.getLength();
        textField.positionCaret(length);
    }

    public void switchSign(TextField textField) {
        String textFieldText = textField.getText();
        boolean textFieldIsEmpty = textFieldText.isEmpty();
        if (textFieldIsEmpty) {
            textField.setText("(-)");
        }
        if (textFieldText.equals("(-)")) {
            textField.setText("");
        }
        if (textFieldText.contains("(-)")) {
            textField.setText(textFieldText.substring(3));
        } else {
            textField.setText(("(-)") + textFieldText);
        }
    }
}