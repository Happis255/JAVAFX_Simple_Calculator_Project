package calculator.Controllers;

import calculator.java_files.Calculator;
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

    @FXML
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

    @FXML
    public void calculate(ActionEvent actionEvent) {

        /* Calculating */
        playSound();
        if (operation.length() != 0)
            try {
                operation += "=";
                String wynik = Calculator.ONP_translate(operation);
                System.out.println("ONP Translate result: " + wynik);
                wynik = Calculator.calculate(wynik);
                result.setText(String.valueOf(wynik));
                operation = wynik;
            } catch (Exception e){
                e.printStackTrace();
                result.setText(String.valueOf("Error"));
                operation = "";
            } finally {
                fresh = true;
            }
        else {
            System.out.println("Nothing to calculate");
        }
    }

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
