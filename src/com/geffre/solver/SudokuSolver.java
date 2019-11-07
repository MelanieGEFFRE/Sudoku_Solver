package com.geffre.solver;

import com.geffre.model.SudokuGrid;

public class SudokuSolver {

    private SudokuGrid gridToSolve;

    public SudokuSolver(SudokuGrid grid){
        gridToSolve = grid;
    }

    public void solve(){
        while (!gridToSolve.isGridSolved()){
            for (int line=0;line<9;line++){
                for (int col=0;col<9;col++){
                    for (Integer wrongAnswers : gridToSolve.intOnLine(line)){
                        gridToSolve.removePossibleAnswer(line, col, wrongAnswers);
                    }
                    for (Integer wrongAnswers : gridToSolve.intOnCol(col)){
                        gridToSolve.removePossibleAnswer(line, col, wrongAnswers);
                    }
                    for (Integer wrongAnswers : gridToSolve.intInSquare(line, col)){
                        gridToSolve.removePossibleAnswer(line, col, wrongAnswers);
                    }
                    gridToSolve.validateAnswers();
                }
            }
        }
        System.out.println(gridToSolve);
    }

}
