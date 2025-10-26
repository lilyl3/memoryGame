package com.lilyl3.memorygame.model;

public class BoardState {
    private String[][] board;
    private Position startPosition;

    public BoardState(String[][] board, Position startPosition) {
        this.board = board;
        this.startPosition = startPosition;
    }

    public String[][] getBoard() { return board; }
    public void setBoard(String[][] board) { this.board = board; }

    public void resetBoardCell(Position pos) {
        board[pos.x][pos.y] = null;
    }

    public Position getStartPosition() { return startPosition; }
    public void setStartPosition(Position startPosition) { this.startPosition = startPosition; } 

    public String getEvent(int x, int y) {
        return board[x][y];
    }
}
