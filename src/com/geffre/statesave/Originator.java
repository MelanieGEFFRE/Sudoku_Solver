package com.geffre.statesave;

import com.geffre.model.SudokuGrid;

public class Originator {
    private SudokuGrid state;

    public void setState(SudokuGrid state) {
        this.state = state;
    }

    public StateSave save() {
        return new StateSave(this.state);
    }

    public void restore(StateSave stateSave) {
        this.state = stateSave.getState();
    }

}
