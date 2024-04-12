package hr.application.models;

import hr.application.gamemanagement.GameSymbol;

public class Player {
    private String name;
    private GameSymbol symbol;
    private boolean isTurn;

    public Player(String name, GameSymbol symbol, boolean isTurn) {
        this.name = name;
        this.symbol = symbol;
        this.isTurn = isTurn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameSymbol getSymbol() {
        return symbol;
    }

    public void setSymbol(GameSymbol symbol) {
        this.symbol = symbol;
    }

    public boolean isTurn() {
        return isTurn;
    }

    public void setTurn(boolean turn) {
        isTurn = turn;
    }
}
