package com.geffre.solver;

import com.geffre.error.SolveError;
import com.geffre.model.SudokuGrid;
import com.geffre.statesave.StateSaver;

public class SudokuSolver {

    private SudokuGrid gridToSolve;
    private StateSaver stateSaver;

    public SudokuSolver(SudokuGrid grid){
        gridToSolve = grid;
        stateSaver = new StateSaver();
    }

    public void solve() throws SolveError {
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
                    if (null == stateSaver.getPreviousStateSaved()){
                        stateSaver.save(gridToSolve);
                    } else {
                        if (gridToSolve.equals(stateSaver.getPreviousStateSaved())) {
                            //On a fait un tour sans rien trouver de nouveau, il faut sauvegarder
                            //Et faire un choix, sur une des cases qui a le moins de possibilités.

                            //Save

                            //On pick la case ayant le moins de solutions possibles
                            //On la remplit avec une solution (on garde de côté toutes les solutions possibles)
                            //On lance le solve avec la nouvelle grille
                            //Si on catch une erreur pour non solution, alors mauvais choix
                            //  On supprime le choix des solutions possibles, et on repart de l'état sauvegardé.
                        }
                    }
                }
            }
        }
        System.out.println(gridToSolve);
    }

}
