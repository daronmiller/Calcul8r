package edu.bsu.calcul8r;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BinaryCalculatorController implements Initializable {
    @FXML
    TextField firstNumberField;

    @FXML
    TextField secondNumberField;

    @FXML
    TextField binaryAnswerField;

    @FXML
    TextField decimalAnswerField;

    @FXML
    Button button0;

    @FXML
    Button clearButton;

    @FXML
    Button backSpaceButton;

    @FXML
    Button button1;

    @FXML
    Button subtractButton;

    @FXML
    Button plusButton;

    @FXML
    Button divideButton;

    @FXML
    Button multiplyButton;

    @FXML
    Button binaryCalculatorButton;

    @FXML
    Button converterButton;

    private TextFieldEditor textFieldEditor = new TextFieldEditor();
    private BinaryDecimalConverter converter = new BinaryDecimalConverter();
    private Calculator calculator = new Calculator();
    private boolean firstNumberFieldIsFocused = true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstNumberField.setOnMouseClicked(event -> firstNumberFieldAction());
        secondNumberField.setOnMouseClicked(event -> secondNumberFieldAction());
        setNumberButtonActions();
        setFunctionButtonActions();
    }

    private void firstNumberFieldAction() {
        firstNumberFieldIsFocused = true;
    }

    private void secondNumberFieldAction() {
        firstNumberFieldIsFocused = false;
    }

    private void setNumberButtonActions() {
        button0.setOnAction(event -> textFieldEditor.updateTextField(getFocusedTextfield(), "0"));
        button1.setOnAction(event -> textFieldEditor.updateTextField(getFocusedTextfield(), "1"));
    }

    private TextField getFocusedTextfield() {
        if (firstNumberFieldIsFocused) {
            return firstNumberField;
        } else {
            return secondNumberField;
        }
    }

    private void setFunctionButtonActions() {
        multiplyButton.setOnMouseClicked(e -> calculateAnswer(multiplyButton));
        divideButton.setOnMouseClicked(e -> calculateAnswer(divideButton));
        plusButton.setOnMouseClicked(e -> calculateAnswer(plusButton));
        subtractButton.setOnMouseClicked(e -> calculateAnswer(subtractButton));
        clearButton.setOnMouseClicked(e -> clearTextfields());
        backSpaceButton.setOnMouseClicked(e -> textFieldEditor.performBackspaceOperation(getFocusedTextfield()));
    }

    private void clearTextfields() {
        firstNumberField.clear();
        secondNumberField.clear();
        binaryAnswerField.clear();
        decimalAnswerField.clear();
    }

    private void calculateAnswer(Button button) {
        String firstNumber = converter.binaryToDecimal(firstNumberField.getText());
        String secondNumber = converter.binaryToDecimal(secondNumberField.getText());
        Double decimalAnswer = calculator.stringToAnswer(firstNumber + button.getText() + secondNumber + "=");
        String decimalAnswerString = decimalAnswer.toString();
        decimalAnswerField.setText(decimalAnswer.toString());
        String binaryAnswer = converter.decimalToBinary(decimalAnswerString.substring(0, decimalAnswerString.length() - 2));
        binaryAnswerField.setText(binaryAnswer);
    }

    @FXML
    private void handleBinaryCalculatorButtonAction(ActionEvent event) throws IOException {
        final String resourceName = "binaryCalculator.fxml";
        switchScene(event, resourceName);
    }

    @FXML
    private void handleCalculatorButtonAction(ActionEvent event) throws IOException {
        final String resourceName = "calculator.fxml";
        switchScene(event, resourceName);
    }

    @FXML
    private void handleConverterButtonAction(ActionEvent event) throws IOException {
        final String resourceName = "converter.fxml";
        switchScene(event, resourceName);
    }

    @FXML
    private void switchScene(ActionEvent event, String resourceName) throws IOException {
        Parent updatedRoot = FXMLLoader.load(getClass().getResource(resourceName));
        Scene updatedScene = new Scene(updatedRoot);
        Stage originalStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        originalStage.hide();
        originalStage.setScene(updatedScene);
        originalStage.show();
    }
}
