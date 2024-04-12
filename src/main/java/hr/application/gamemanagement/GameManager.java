package hr.application.gamemanagement;

import hr.application.ai.AIPlayer;
import hr.application.alerts.GameOverAlert;
import hr.application.models.Board;
import hr.application.models.Player;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;

public class GameManager {
    private static GameManager instance;
    private Player player;
    private AIPlayer aiPlayer;
    private Board board;
    private Label scoreLabel;
    private int playerScore;
    private int aiScore;

    private GameManager() {
        playerScore = 0;
        aiScore = 0;
    }

    public static GameManager getInstance() {
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    public void startGame(Board board, Label scoreLabel) {
        this.board = board;
        scoreLabel.setText(makeScore());
        this.scoreLabel = scoreLabel;

        if (!player.isTurn())
            aiPlayer.makeMove();
    }

    public void switchPlayer() {
        if (player.getSymbol().equals(GameSymbol.CROSS)) {
            if (player.isTurn()) {
                player.setTurn(false);
                aiPlayer.setTurn(true);
                aiPlayer.makeMove();
            } else {
                player.setTurn(true);
                aiPlayer.setTurn(false);
            }
        } else {
            if (aiPlayer.isTurn()) {
                aiPlayer.setTurn(false);
                player.setTurn(true);
            } else {
                aiPlayer.setTurn(true);
                player.setTurn(false);
                aiPlayer.makeMove();
            }
        }
    }

    public void updateGameState(GameSymbol winner) {
        new GameOverAlert().make(winner).showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                board.clear();

                if (player.getSymbol().equals(winner)) {
                    playerScore++;
                } else if (aiPlayer.getSymbol().equals(winner)) {
                    aiScore++;
                }

                switchPlayer();

                scoreLabel.setText(makeScore());
            }
        });
    }

    public void setPlayer(Player player) {
        this.player = player;
        aiPlayer = new AIPlayer(this.player.getSymbol().equals(GameSymbol.CROSS)
                ? GameSymbol.CIRCLE : GameSymbol.CROSS, !this.player.isTurn());
    }

    public Player getPlayer() {
        return player;
    }

    private String makeScore() {
        return playerScore + " : " + aiScore;
    }

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public Board getBoard() {
        return board;
    }

    public AIPlayer getAI() {
        return aiPlayer;
    }

    public void resetGame() {
        board = null;
        player = null;
        aiPlayer = null;
        scoreLabel = null;
        playerScore = 0;
        aiScore = 0;
    }

}
