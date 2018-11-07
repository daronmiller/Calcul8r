package edu.bsu.calcul8r;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

@SuppressWarnings("Convert2streamapi")
public class CalculatorController implements Initializable {
    @FXML
    Button converterButton;

    @FXML
    Button binaryCalculatorButton;

    @FXML
    Button calculatorButton;

    @FXML
    Button button0;

    @FXML
    Button button1;

    @FXML
    Button button2;

    @FXML
    Button button3;

    @FXML
    Button button4;

    @FXML
    Button button5;

    @FXML
    Button button6;

    @FXML
    Button button7;

    @FXML
    Button button8;

    @FXML
    Button button9;

    @FXML
    Button powerButton;

    @FXML
    Button multiplyButton;

    @FXML
    Button divideButton;

    @FXML
    Button plusButton;

    @FXML
    Button subtractButton;

    @FXML
    Button decimalButton;

    @FXML
    Button signButton;

    @FXML
    Button equalsButton;

    @FXML
    Button clearButton;

    @FXML
    Button backSpaceButton;

    @FXML
    TextField textField;

    @FXML
    TextField displayField;

    private TextFieldEditor textFieldEditor = new TextFieldEditor();
    private Calculator calculator = new Calculator();
    private List<Button> numberButtons = new ArrayList<>();
    private List<Button> functionButtons = new ArrayList<>();
    private List<KeyCode> operationCodes = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        populateLists();
        setNumberButtonActions();
        setFunctionButtonActions();
        setClearTextFieldAction();
        EventHandler<KeyEvent> keyEventHandler = this::filterKeyCode;
        textField.setOnKeyReleased(keyEventHandler);
    }

    private void populateLists() {
        numberButtons.addAll(Arrays.asList(decimalButton, button0, button1, button2, button3, button4,
                button5, button6, button7, button8, button9));
        functionButtons.addAll(Arrays.asList(powerButton, multiplyButton, divideButton, plusButton, subtractButton));
        operationCodes.addAll(Arrays.asList(KeyCode.EQUALS, KeyCode.SHIFT, KeyCode.PLUS, KeyCode.MINUS,
                KeyCode.SLASH, KeyCode.ASTERISK, KeyCode.CIRCUMFLEX));
    }

    private void setNumberButtonActions() {
        for (Button button : numberButtons) {
            String buttonText = button.getText();
            button.setOnAction(event -> textFieldEditor.updateTextField(textField, buttonText));
        }
    }

    private void setFunctionButtonActions() {
        signButton.setOnMouseClicked(e -> textFieldEditor.switchSign(textField));
        equalsButton.setOnAction(e -> textFieldEditor.updateTextField(displayField, textField.getText() + "="));
        equalsButton.setOnMouseClicked(e -> textField.setText(String.valueOf(calculator.stringToAnswer(displayField.getText()))));
        clearButton.setOnAction(e -> textField.setText(""));
        clearButton.setOnMouseClicked(e -> displayField.setText(""));
        clearButton.setOnMouseReleased(e -> textField.requestFocus());
        backSpaceButton.setOnAction(e -> textFieldEditor.performBackspaceOperation(textField));
    }

    private void setClearTextFieldAction() {
        for (Button button : functionButtons) {
            String buttonText = button.getText();
            button.setOnMouseClicked(e -> textField.setText(""));
            button.setOnAction(e -> textFieldEditor.updateTextField(displayField, textField.getText() + buttonText));
        }
    }

    private void filterKeyCode(KeyEvent keyEvent) {
        for (KeyCode keyCode : operationCodes) {
            if (keyEvent.getCode().equals(keyCode)) {
                adjustFields();
            }
        }
        if (keyEvent.getCode() == KeyCode.EQUALS) {
            adjustFields();
        }
    }

    private void adjustFields() {
        textField.requestFocus();
        String currentText = textField.getText();
        textField.setText("");
        String displayString = displayField.getText() + currentText;
        displayField.setText(displayString);
        if (displayField.getText().contains("=")) {
            int equalsIndex = displayField.getText().indexOf('=') + 1;
            displayField.setText(displayField.getText().substring(0, equalsIndex));
            textField.setText(String.valueOf(calculator.stringToAnswer(displayField.getText())));
        }
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