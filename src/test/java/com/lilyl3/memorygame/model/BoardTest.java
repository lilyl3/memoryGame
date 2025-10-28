package com.lilyl3.memorygame.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;

public class BoardTest {
    private Board board;
    private String[] events;

    @BeforeEach
    void setup(){
        events = new String[]{"Hole", "Pit", "Gold", "Silver", "Diamond", "Lion", "Flowers"};
        board = new Board(3, 4, events);
    }

    @Test
    void testInitializationAndReset() {
        BoardState state = board.getBoardState();
        Set<String> boardEvents = new HashSet<>();
        Set<String> inputEvents = new HashSet<>(List.of(events));
        List<Position> eventIndices = new ArrayList<>();

        // Check if all events are on the board
        for (int r = 0; r < board.getHeight(); r++){
            for (int c = 0; c < board.getWidth(); c++){
                String event = state.getEvent(r, c);
                if (event != null) {
                    boardEvents.add(event);
                    eventIndices.add(new Position(r, c));
                }
            }
        }
        assertEquals(inputEvents, boardEvents, "The board events should match the input events");

        // Check if board cell is reset
        Position eventPos = eventIndices.get(0);
        assertNotNull(
            state.getEvent(eventPos.x, eventPos.y),
            "This board cell had an event!"
        );
        state.resetBoardCell(eventPos);
        assertNull(
            state.getEvent(eventPos.x, eventPos.y),
            "This board cell should be reset"
        );
    }
}
