package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.AccessibleAction;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {
    @FXML
    private Label screen;
    private boolean start = true;
    private String operator = "";
    private double number1 = 0;
    private double number2 = 0;
    private double output = 0;
    private double decimalnum = 0;
    private boolean hasDot = false;
    private boolean isFillednum1 = false;
    private boolean isFillednum2 = false;


    @FXML
    public void numberClick(ActionEvent event) {
        if (start) {
            screen.setText("");
            start = false;
        }
        String value = ((Button)event.getSource()).getText();
        screen.setText(screen.getText() + value);
    }

    @FXML
    public void operatorClick(ActionEvent event) {
        String value = ((Button) event.getSource()).getText();

        if (!value.equals("=")) {
            if (!operator.isEmpty()) {
                return;
            }
            operator = value;
            number1 = Double.parseDouble(screen.getText());
            isFillednum1 = true;
            isFillednum2 = false;
            screen.setText("");
            hasDot = false;

        }
        else {
            if (operator.isEmpty()){
                return;
            }
            number2 = Double.parseDouble(screen.getText());
            isFillednum2 = true;
            output = calculate(number1, number2, operator);
            screen.setText(String.valueOf(output));
            reset();
        }
    }

    private void reset(){
        operator = "";
        start = true;
        hasDot = false;
    }

    @FXML
    public void clear() {
        number1 = 0;
        number2 = 0;
        operator = "";
        screen.setText("");
        hasDot = false;
        isFillednum1 = false;
        isFillednum2 = false;
    }

    @FXML
    public void dotClick(){
        if(!hasDot) {
            screen.setText(screen.getText() + ".");
            hasDot = true;
        }
        else{
            String value = screen.getText();
            decimalnum = Double.parseDouble(value);
            if(isFillednum1)
                number2 = decimalnum;
            else
                number1 = decimalnum;
        }

    }

    private double calculate(double x1, double x2, String op) {
        switch (op){
            case "+":
                return  Math.round((x1+x2) * 1000.0) / 1000.0;
            case "-":
                return Math.round((x1-x2) * 1000.0) / 1000.0;
            case "x":
                return Math.round((x1*x2) * 1000.0) / 1000.0;
            case "÷":
                if(number2 == 0)
                    return 0;
                return Math.round((x1/x2) * 1000.0) / 1000.0;
            default:
                return 0;
        }
    }
}
