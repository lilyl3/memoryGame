package com.lilyl3.memorygame.model;
import org.springframework.stereotype.Component;

@Component
public class Player {
    private Position position;
    private int score;
    private Boolean isDone;
    private Status lastMoveStatus;

    public Player(){
        this.position = new Position();
        this.score = 100;
        this.isDone = false;
        this.lastMoveStatus = Status.NA;
    }

    public Player(int x, int y){
        this.position = new Position(x, y);
        this.score = 100;
        this.isDone = false;
        this.lastMoveStatus = Status.NA;
    }

    public Position getPosition() { return position; }
    public void setPosition(Position position) { this.position = position; }

    public int getScore(){ return score; }
    public void setScore(int score) { this.score = score; }
    public void updateScore(int points) { score += points; }

    public Boolean getisDone() { return isDone; }
    public void setisDone(Boolean isDone) {this.isDone = isDone; }

    public Status getLastMoveStatus() { return lastMoveStatus; }
    public void setLastMoveStatus(Status newStatus) {lastMoveStatus = newStatus; }

    public int getX(){ return position.x; }
    public int getY(){ return position.y; }

    public void updateX(int deltaX){ this.position.x += deltaX; }
    public void updateY(int deltaY){ this.position.y += deltaY; }
}
