package hr.application.tictactoe;

import hr.application.gamemanagement.GameSymbol;
import hr.application.gamemanagement.GameManager;
import hr.application.models.Board;
import hr.application.models.Player;
import hr.application.scenemanagement.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GameController {
    private static final String PLAYER_X_COLOR = "#294B94";
    private static final String PLAYER_O_COLOR = "#C21619";

    @FXML private Label score;
    @FXML private Label aiName;
    @FXML private ImageView aiIcon;
    @FXML private Label playerName;
    @FXML private ImageView playerIcon;
    @FXML private GridPane playGrid;

    public void initialize() {
        Board board = new Board(playGrid);
        board.initializeBoard();
        initializePlayerInfo();
        GameManager.getInstance().startGame(board, score);
    }

    private void initializePlayerInfo() {
        Player currentPlayer = GameManager.getInstance().getPlayer();
        Player aiPlayer = GameManager.getInstance().getAI();

        setPlayerInfo(currentPlayer, playerIcon, playerName);
        setPlayerInfo(aiPlayer, aiIcon, aiName);
    }

    private void setPlayerInfo(Player player, ImageView icon, Label name) {
        Image iconImage = player.getSymbol() == GameSymbol.CROSS ? GameSymbol.CROSS.getIcon() : GameSymbol.CIRCLE.getIcon();
        String color = player.getSymbol() == GameSymbol.CROSS ? PLAYER_X_COLOR : PLAYER_O_COLOR;

        icon.setImage(iconImage);
        name.setText(player.getName());
        name.setStyle("-fx-text-fill: " + color);
    }

    public void onExitButton() {
        GameManager.getInstance().resetGame();
        SceneManager.getInstance().setPreviousScene();
    }

}