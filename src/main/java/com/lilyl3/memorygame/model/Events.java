package com.lilyl3.memorygame.model;
import org.springframework.stereotype.Component;

@Component
public class Events {
    private String[] events;
    private int nextEventIdx;

    public String[] getEvents(){
        return events;
    }

    public Events(String[] events) {
        this.nextEventIdx = 0;
        this.events = events;
    }

    public String getNextEvent(){
        return events[nextEventIdx];
    }

    public void updateNextEvent(){
        nextEventIdx++;
    }

    public Boolean isDone() {
        return nextEventIdx == events.length;
    }
}
