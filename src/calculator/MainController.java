package calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainController {

    @FXML
    private Label result;

    private long number1 = 0;
    private String operator = "";
    private boolean start = true;
    private Model model = new Model();
    @FXML
    public void processNumber(ActionEvent event){
        if (start){
            result.setText("");
            start = false;
        }
        /* Getting value from the button text */
        String value = ((Button)event.getSource()).getText();
        result.setText(result.getText() + value);
    }

    @FXML
    public void processOperators(ActionEvent event){
        String value = ((Button)event.getSource()).getText();
        if (!value.equals("=")) {
            if (!operator.isEmpty()) return;
            operator = value;
            number1 = Long.parseLong(result.getText());

            /* Clear the text area */
            result.setText("");

        } else {
            if (operator.isEmpty()) return;
            long number2 = Long.parseLong(result.getText());
            float result_cal = model.Calculate(number1, number2, operator);
            result.setText(String.valueOf(result_cal));
            operator = "";
            start = true;
        }
    }
}
