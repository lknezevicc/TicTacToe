package hr.application.models;

import hr.application.gamemanagement.GameSymbol;
import hr.application.gamemanagement.GameManager;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Board {

    private GridPane playGrid;

    public Board(GridPane playGrid) {
        this.playGrid = playGrid;
    }

    public void initializeBoard() {
        playGrid.getChildren().stream()
                .filter(node -> node instanceof Button)
                .forEach(node -> configureButton((Button) node));
    }

    private void configureButton(Button button) {
        button.setGraphic(new ImageView() {
            {
                setFitHeight(100);
                setFitWidth(100);
                setImage(new Image(GameSymbol.EMPTY.getIcon().getUrl()));
            }
        });

        button.setOnAction(e -> {
            Player currentPlayer = GameManager.getInstance().getPlayer();
            if (currentPlayer.isTurn()) {
                Image image = currentPlayer.getSymbol().getIcon();
                ImageView imageView = new ImageView() {
                    {
                        setImage(image);
                        setFitHeight(100);
                        setFitWidth(100);
                    }
                };
                button.setGraphic(imageView);
                button.setDisable(true);
                button.setOpacity(1);

                GameManager.getInstance().switchPlayer();
            }
        });
    }

    public GridPane getPlayGrid() {
        return playGrid;
    }

    public char[][] getBoardState() {
        char[][] board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = (Button) playGrid.getChildren().get(i * 3 + j);
                ImageView tempImageView = (ImageView) button.getGraphic();
                Image tempImage = tempImageView.getImage();

                board[i][j] = tempImage.getUrl().contains(GameSymbol.CROSS.getIcon().getUrl()) ? 'X'
                        : tempImage.getUrl().contains(GameSymbol.CIRCLE.getIcon().getUrl()) ? 'O'
                        : '-';
            }
        }

        return board;
    }

    public void clear() {
        playGrid.getChildren().stream()
                .filter(node -> node instanceof Button)
                .forEach(node -> {
                    Button button = (Button) node;
                    button.setGraphic(new ImageView() {
                        {
                            setFitHeight(100);
                            setFitWidth(100);
                            setImage(new Image(GameSymbol.EMPTY.getIcon().getUrl()));
                        }
                    });
                    button.setDisable(false);
                    button.setOpacity(1);
                });
    }

}
