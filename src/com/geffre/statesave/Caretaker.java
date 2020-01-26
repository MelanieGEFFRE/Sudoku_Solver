package com.geffre.statesave;

import javax.swing.plaf.nimbus.State;
import java.util.ArrayList;

public class Caretaker {
    private ArrayList<StateSave> stateSaves = new ArrayList<>();

    public void addStateSave(StateSave stateSave) {
        stateSaves.add(stateSave);
    }

    public StateSave getStateSaved() {
        return stateSaves.get(stateSaves.size()-1);
    }
}
