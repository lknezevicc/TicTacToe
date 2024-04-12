package hr.application.tictactoe;

import hr.application.alerts.InvalidInputAlert;
import hr.application.gamemanagement.GameSymbol;
import hr.application.gamemanagement.GameManager;
import hr.application.models.Player;
import hr.application.scenemanagement.SceneManager;
import hr.application.scenemanagement.SceneType;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

public class StartController {
    private ImageView selectedPlayer;

    @FXML private ImageView playerX;
    @FXML private ImageView playerO;
    @FXML private TextField playerName;

    public void initialize() {
        Tooltip.install(playerName, new Tooltip("""
            Name can only be 3-10 characters long
            and can only contain letters and numbers."""));
        configurePlayersImageView(playerX);
        configurePlayersImageView(playerO);
    }

    private void configurePlayersImageView(ImageView player) {
        player.setOpacity(0.3);

        player.setOnMouseClicked(e -> {
            selectedPlayer = player;
            player.setOpacity(1);
            if (player == playerX) {
                playerO.setOpacity(0.3);
            } else {
                playerX.setOpacity(0.3);
            }
        });

        player.hoverProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                player.setCursor(Cursor.HAND);
                player.setOpacity(1);
            } else if (selectedPlayer != player) {
                player.setOpacity(0.3);
            }
        });
    }

    public void onStartButtonClicked() {
        if (nothingIsSelected()) {
            new InvalidInputAlert().make("Please select a player and enter a player name!").showAndWait();
            return;
        }

        if (playerName.getText().isEmpty() || !playerName.getText().matches("^[a-zA-Z0-9čćđšžČĆĐŠŽ]{3,10}$")) {
            new InvalidInputAlert().make("Name can only be 3-10 characters long and can only contain letters and numbers.").showAndWait();

            return;
        }

        if (selectedPlayer == null) {
            new InvalidInputAlert().make("Please select a player!").showAndWait();
            return;
        }

        if (selectedPlayer == playerX) {
            GameManager.getInstance().setPlayer(new Player(playerName.getText(), GameSymbol.CROSS, true));
        } else {
            GameManager.getInstance().setPlayer(new Player(playerName.getText(), GameSymbol.CIRCLE, false));
        }

        SceneManager.getInstance().setScene(SceneType.GAME);
    }

    private boolean nothingIsSelected() {
        return playerName.getText().isEmpty() && selectedPlayer == null;
    }

}
