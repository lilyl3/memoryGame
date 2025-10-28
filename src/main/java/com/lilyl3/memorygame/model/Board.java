package com.lilyl3.memorygame.model;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Board {
    private int height;
    private int width;
    private Events events;
    private BoardState boardState;

    private void initBoardState(){
        Random rand = new Random();
        Set<Position> uniqPos = new HashSet<>();
        Position startPosition = new Position(rand.nextInt(this.width), rand.nextInt(height));

        String[][] board = new String[height][width];
        for (String event : this.events.getEvents()) {
            Position pos;
            do {
                pos = new Position(rand.nextInt(this.height), rand.nextInt(width));
            } while (uniqPos.contains(pos) || pos.equals(startPosition));
        
            uniqPos.add(pos);
            board[pos.x][pos.y] = event;
        }        

        this.boardState = new BoardState(board, startPosition);
    }

    public Board(int height, int width, String[] events) {
        this.height = height;
        this.width = width;
        this.events = new Events(events);
        initBoardState();
    }

    public int getHeight() { return height; };
    public int getWidth() { return width; }

    public final BoardState getBoardState() { return boardState;}
    public final Position getStartingPostion() {return boardState.getStartPosition(); }

    public Boolean isXWithinBounds(int x) { return x >= 0 && x < height; }
    public Boolean isYWithinBounds(int y) { return y >= 0 && y < width; }

    public Status isNextEvent(Position position){
        String event = boardState.getEvent(position.x, position.y);
        if (event == null) {
            return Status.NA;
        } else if (events.getNextEvent().equals(event)) {
            events.updateNextEvent();
            boardState.resetBoardCell(position);
            return Status.CORRECT;
        } else {
            return Status.INCORRECT;
        }
    }

    public Boolean isPlayerDone(){
        return events.isDone();
    }
}
