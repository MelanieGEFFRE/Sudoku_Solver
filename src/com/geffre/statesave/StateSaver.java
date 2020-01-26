package com.geffre.statesave;

import com.geffre.model.SudokuGrid;

public class StateSaver {
    private Originator originator;
    private Caretaker caretaker;

    public StateSaver(){
        this.originator=new Originator();
        this.caretaker=new Caretaker();
    }

    public void save(SudokuGrid state){
        originator.setState(state);
        caretaker.addStateSave(originator.save());
    }

    public SudokuGrid getPreviousStateSaved(){
        originator.restore(caretaker.getStateSaved());
        return originator.save().getState();
    }
}
