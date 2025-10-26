package com.lilyl3.memorygame.controller;

import org.springframework.web.bind.annotation.*;
import com.lilyl3.memorygame.service.GameLogic;
import com.lilyl3.memorygame.model.BoardConfig;
import com.lilyl3.memorygame.model.BoardState;
import com.lilyl3.memorygame.model.MoveRequest;
import com.lilyl3.memorygame.model.Player;

@RestController
@RequestMapping("/game")
public class GameController {
    private static GameLogic gameLogic;

    @PostMapping("/init")
    public BoardState initGame(@RequestBody BoardConfig config) {
        System.out.println("height: " + config.getHeight());
        System.out.println("width: " + config.getWidth());
        gameLogic = new GameLogic(config.getHeight(), config.getWidth(), config.getEvents());
        return gameLogic.getInitBoardState();
    }
    
    @PostMapping("/update")
    public Player movePlayer(@RequestBody MoveRequest request) {
        gameLogic.movePlayer(request.getAction());
        gameLogic.updatePlayerScore();
        Player player = gameLogic.getPlayerStatus();
        // System.out.println("Returning player: " + player.getLastMoveStatus());

        return player;
    }
}
