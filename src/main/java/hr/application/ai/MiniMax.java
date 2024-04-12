package hr.application.ai;

import hr.application.models.Move;

public class MiniMax {

    public MiniMax() {}

    public boolean anyMovesLeft(char[][] board) {
        for (char[] row : board) {
            for (char c : row) {
                if (c == '-') {
                    return true;
                }
            }
        }

        return false;
    }

    public Move findBestMove(char[][] board, char aiPlayer) {
        char humanPlayer = (aiPlayer == 'X') ? 'O' : 'X';
        int bestValue = Integer.MIN_VALUE;
        Move bestMove = new Move(-1, -1);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    board[i][j] = aiPlayer;

                    int moveVal = minimax(board, 0, true, aiPlayer, humanPlayer);

                    board[i][j] = '-';

                    if (moveVal > bestValue)
                    {
                        bestMove.setRow(i);
                        bestMove.setCol(j);
                        bestValue = moveVal;
                    }
                }
            }
        }

        return bestMove;
    }

    private int minimax(char[][] board, int depth, boolean isMax, char aiPlayer, char humanPlayer) {
        int boardState = evaluateBoard(board, aiPlayer, humanPlayer);

        if (boardState == 10) {
            return boardState;
        }

        if (boardState == -10) {
            return boardState;
        }

        if (!anyMovesLeft(board)) {
            return 0;
        }

        int bestScore;
        if (isMax) {
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '-') {
                        board[i][j] = aiPlayer;
                        bestScore = Math.max(bestScore, minimax(board, depth + 1, false, aiPlayer, humanPlayer));
                        board[i][j] = '-';
                    }
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '-') {
                        board[i][j] = humanPlayer;
                        bestScore = Math.min(bestScore, minimax(board, depth + 1, true, aiPlayer, humanPlayer));
                        board[i][j] = '-';
                    }
                }
            }
        }

        return bestScore;
    }

    public int evaluateBoard(char[][] board, char aiPlayer, char humanPlayer) {

        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0] == aiPlayer) {
                    return 10;
                } else if (board[row][0] == humanPlayer) {
                    return -10;
                }
            }
        }

        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                if (board[0][col] == aiPlayer) {
                    return 10;
                } else if (board[0][col] == humanPlayer) {
                    return -10;
                }
            }
        }

        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == aiPlayer) {
                return 10;
            } else if (board[0][0] == humanPlayer) {
                return -10;
            }
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == aiPlayer) {
                return 10;
            } else if (board[0][2] == humanPlayer) {
                return -10;
            }
        }

        return 0;
    }

}
