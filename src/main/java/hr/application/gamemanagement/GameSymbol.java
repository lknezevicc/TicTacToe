package hr.application.gamemanagement;

import javafx.scene.image.Image;

import java.io.File;

public enum GameSymbol {
    CROSS('X', "src/main/resources/img/playerX.png"),
    CIRCLE('O', "src/main/resources/img/playerO.png"),
    EMPTY('-', "src/main/resources/img/blank.png");

    private final char symbol;
    private final String iconPath;

    GameSymbol(char symbol, String iconPath) {
        this.symbol = symbol;
        this.iconPath = iconPath;
    }

    public char getSymbol() {
        return symbol;
    }

    public Image getIcon() {
        return new Image(new File(iconPath).toURI().toString());
    }
}