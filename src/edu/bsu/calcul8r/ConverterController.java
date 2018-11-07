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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ConverterController implements Initializable {
    @FXML
    Button convertButton;

    @FXML
    Button converterButton;

    @FXML
    Button binaryCalculatorButton;

    @FXML
    Button calculatorButton;

    @FXML
    Button button6;

    @FXML
    Button button4;

    @FXML
    Button button5;

    @FXML
    Button button0;

    @FXML
    Button button8;

    @FXML
    Button button9;

    @FXML
    Button button7;

    @FXML
    Button clearButton;

    @FXML
    Button backSpaceButton;

    @FXML
    Button button2;

    @FXML
    Button button3;

    @FXML
    Button button1;

    @FXML
    TextField decimalField;

    @FXML
    TextField binaryField;

    private TextFieldEditor textFieldEditor = new TextFieldEditor();
    private BinaryDecimalConverter converter = new BinaryDecimalConverter();
    private List<Button> buttons = new ArrayList<>();
    private boolean binaryFieldIsFocused = true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttons.addAll(Arrays.asList(button2, button3, button4, button5, button6, button7, button8, button9));
        binaryField.setOnMouseClicked(event -> binaryFieldAction());
        decimalField.setOnMouseClicked(event -> decimalFieldAction());
        setButtonActions();
    }

    private void binaryFieldAction() {
        binaryFieldIsFocused = true;
        decimalField.clear();
    }

    private void decimalFieldAction() {
        binaryFieldIsFocused = false;
        binaryField.clear();
    }

    private void setButtonActions() {
        button0.setOnAction(event -> textFieldEditor.updateTextField(getFocusedTextfield(), "0"));
        button1.setOnAction(event -> textFieldEditor.updateTextField(getFocusedTextfield(), "1"));
        clearButton.setOnMouseClicked(e -> clearTextfields());
        backSpaceButton.setOnMouseClicked(e -> textFieldEditor.performBackspaceOperation(getFocusedTextfield()));
        convertButton.setOnMouseClicked(e -> convert());
        setDecimalButtonActions(buttons);
    }

    private TextField getFocusedTextfield() {
        if (binaryFieldIsFocused) {
            return binaryField;
        } else {
            return decimalField;
        }
    }

    private void clearTextfields() {
        decimalField.clear();
        binaryField.clear();
    }

    private void setDecimalButtonActions(List<Button> buttons) {
        for (Button button : buttons) {
            String buttonText = button.getText();
            button.setOnAction(event -> textFieldEditor.updateTextField(decimalField, buttonText));
        }
    }

    private void convert() {
        String binaryFieldText = binaryField.getText();
        String decimalFieldText = decimalField.getText();
        if (binaryFieldText.isEmpty()) {
            String answer = converter.decimalToBinary(decimalFieldText);
            binaryField.setText(answer);
        } else {
            String answer = converter.binaryToDecimal(binaryFieldText);
            decimalField.setText(answer);
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
