package calculator.Controllers;

import calculator.java_files.ONPCalculator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javax.sound.sampled.*;
import java.io.File;

public class MainController {

    @FXML
    private Label result;

    private File yourFile = new File("src/click.wav");
    private boolean fresh = true;
    private String operation = "";

    @FXML //Adding the button symbol to the operation
    public void processSymbol(ActionEvent event){
        playSound();

        if (fresh){
            result.setText("");
            fresh = false;
        }

        System.out.println("Adding: " + ((Button)event.getSource()).getText());
        operation += ((Button)event.getSource()).getText();
        result.setText(operation);
    }

    @FXML //Calculating operation
    public void calculate(ActionEvent actionEvent) {
        playSound();
        if (operation.length() != 0)
            try {
                operation += "=";

                /* Translate operation to ONP */
                String result_value = ONPCalculator.ONP_translate(operation);
                System.out.println("ONP Translate result: " + result_value);

                /* Calculating ONP operation */
                result_value = ONPCalculator.ONP_calculate(result_value);

                /* Sending the result to the text label */
                result.setText(String.valueOf(result_value));
                operation = result_value;
            } catch (Exception e){

                /* If the error occurs - reset */
                System.out.println(e.getMessage());
                result.setText(String.valueOf("Syntax Err"));
                operation = "";
                fresh = true;
            }
        else {
            System.out.println("Nothing to calculate");
        }
    }

    /* Removing the last symbol from the operation */
    public void undo(ActionEvent actionEvent) {
        playSound();
        if (!fresh){
            System.out.println("Removing last value");
            operation = operation.substring(0, operation.length()-1);
            result.setText(operation);
            if (operation.length()==0)
                fresh = true;
        } else {
            System.out.println("Nothing to remove");
        }
    }

    /* Reset the operation */
    public void reset(ActionEvent actionEvent) {
        playSound();
        System.out.println("Reset");
        result.setText("");
        operation = "";
        fresh = true;
    }

    /* Playes click sound */
    private void playSound(){
        try {
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            Clip clip;
            stream = AudioSystem.getAudioInputStream(yourFile);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
