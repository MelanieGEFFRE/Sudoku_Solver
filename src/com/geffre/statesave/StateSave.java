package com.geffre.statesave;

import com.geffre.model.SudokuGrid;

/**
 * Using memento pattern.
 */
public class StateSave {
    private SudokuGrid state;

    public StateSave(SudokuGrid state) {
        this.state = state;
    }

    public SudokuGrid getState(){
        return state;
    }
}
