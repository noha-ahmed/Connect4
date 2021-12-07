package Frontend;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class homePage {
    @FXML
    public Label warning;
    @FXML
    public TextField input;
    @FXML
    public ComboBox<String> playerTurn;
    @FXML
    public ComboBox<String> strategy;

    public void Start(ActionEvent event) {
        Main Scene = new Main();
        try {
            int val = Integer.parseInt(input.getText());
            if (val >= 0) {
                // warning.setVisible(false);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
                Parent root = loader.load();
                Controller c = loader.getController();
                Scene.setScene(event, root, "Connect4");
                if (playerTurn.getValue() == null) playerTurn.setValue("Player");
                if (strategy.getValue() == null) strategy.setValue("with alpha-beta pruning");
                c.setSettings(playerTurn.getValue(), strategy.getValue() , val);


            } else {
                warning.setVisible(true);
                warning.setText("please enter a valid level value");
            }

        } catch (IOException e) {
            warning.setVisible(true);
            warning.setText("please enter a valid level value");
            e.printStackTrace();
        }
    }

}
