package com.lilyl3.memorygame.service;

import com.lilyl3.memorygame.model.*;
import java.util.Random;

public class GameLogic {
    private Player player;
    private Board board;
    private Random rand;

    public GameLogic(int height, int width, String[] events) {
        board = new Board(height, width, events);
        Position startingPos = board.getStartingPostion();

        this.player = new Player(startingPos.x, startingPos.y);
        this.rand = new Random();
    }

    public final Position movePlayer(String direction) {
        // Update player's position
        switch(direction) {
            case "Left":
                if (board.isYWithinBounds(player.getY() - 1)) player.updateY(-1);
                break;
            case "Right":
                if (board.isYWithinBounds(player.getY() + 1)) player.updateY(1);
                break;
            case "Up":
                if (board.isXWithinBounds(player.getX() - 1)) player.updateX(-1);
                break;
            case "Down":
                if (board.isXWithinBounds(player.getX() + 1)) player.updateX(1);
                break;
            default:
                break;
        }
        return player.getPosition();
    }

    public int updatePlayerScore(){
        // Update player's score
        Status moveStatus = board.isNextEvent(player.getPosition());
        switch (moveStatus) {
            case CORRECT:
                player.updateScore(rand.nextInt(50) + 1);
                if (board.isPlayerDone()) player.setisDone(true);
                break;
            case INCORRECT:
                player.updateScore(-1 * (rand.nextInt(50) + 1));
                break;
            default:
                break;
        }
        // Update the status of the player's last move
        player.setLastMoveStatus(moveStatus);

        return player.getScore();
    }

    public final Player getPlayerStatus(){
        return player;
    }

    public final BoardState getInitBoardState(){
        return board.getBoardState();
    }
}
