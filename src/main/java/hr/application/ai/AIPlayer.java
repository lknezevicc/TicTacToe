package hr.application.ai;

import hr.application.gamemanagement.GameSymbol;
import hr.application.gamemanagement.GameManager;
import hr.application.models.Move;
import hr.application.models.Player;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class AIPlayer extends Player {
    private static final String DEFAULT_NAME = "AI";
    private final MiniMax miniMax;

    public AIPlayer(GameSymbol symbol, boolean isTurn) {
        super(DEFAULT_NAME, symbol, isTurn);
        miniMax = new MiniMax();
    }

    public void makeMove() {
        char playerSymbol = GameManager.getInstance().getPlayer().getSymbol().getSymbol();
        char aiSymbol = GameManager.getInstance().getAI().getSymbol().getSymbol();

        // check if the human player has won
        if (miniMax.evaluateBoard(GameManager.getInstance().getBoard().getBoardState(), aiSymbol, playerSymbol) == -10) {
            GameManager.getInstance().updateGameState(GameManager.getInstance().getPlayer().getSymbol());
            if (aiSymbol == 'X') GameManager.getInstance().switchPlayer();
            return;
        }

        if (miniMax.anyMovesLeft(GameManager.getInstance().getBoard().getBoardState())) {
            if (GameManager.getInstance().getAI().isTurn()) {
                // AI makes move
                Move bestMove = miniMax.findBestMove(GameManager.getInstance().getBoard().getBoardState(), aiSymbol);
                Button button = (Button) GameManager.getInstance().getBoard().getPlayGrid().getChildren().get(bestMove.getRow() * 3 + bestMove.getCol());
                button.setGraphic(new ImageView() {
                    {
                        setImage(GameManager.getInstance().getAI().getSymbol().getIcon());
                        setFitHeight(100);
                        setFitWidth(100);
                    }
                });
                button.setDisable(true);
                button.setOpacity(1);

                // check if AI has won
                if (miniMax.evaluateBoard(GameManager.getInstance().getBoard().getBoardState(), aiSymbol, playerSymbol) == 10) {
                    GameManager.getInstance().updateGameState(GameManager.getInstance().getAI().getSymbol());
                    if (aiSymbol == 'X') GameManager.getInstance().switchPlayer();
                    return;
                }

                // check if it's draw
                if (!miniMax.anyMovesLeft(GameManager.getInstance().getBoard().getBoardState())) {
                    GameManager.getInstance().updateGameState(GameSymbol.EMPTY);
                    if (aiSymbol == 'X') GameManager.getInstance().switchPlayer();
                    return;
                }

            }
        } else {
            // it's a draw
            GameManager.getInstance().updateGameState(GameSymbol.EMPTY);
            if (aiSymbol == 'X') GameManager.getInstance().switchPlayer();
            return;
        }

        GameManager.getInstance().switchPlayer();
    }

}
