package hr.application.alerts;

import hr.application.gamemanagement.GameSymbol;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class GameOverAlert {
    public Alert make(GameSymbol winner) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Label label = new Label();
        label.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 20));
        alert.setGraphic(null);
        alert.setTitle("End of game");

        switch (winner) {
            case CROSS:
                label.setText("Player X won!");
                break;
            case CIRCLE:
                label.setText("Player O won!");
                break;
            default:
                label.setText("It's a draw!");
                break;
        }

        alert.getDialogPane().setContent(new VBox() {
            {
                getChildren().addAll(label);
                setAlignment(Pos.CENTER);
            }
        });

        alert.setHeaderText(null);
        alert.getDialogPane().setPrefHeight(200);
        alert.getDialogPane().setPrefWidth(300);

        return alert;
    }
}