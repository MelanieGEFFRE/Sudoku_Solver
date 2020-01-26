package com.geffre.model;

import com.geffre.error.SolveError;

import java.util.ArrayList;
import java.util.List;

public class SudokuGrid {

    static final int[] POSSIBLE_ANSWERS = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private SudokuCase[][] grid;

    public SudokuGrid() {
        grid = new SudokuCase[9][9];
    }

    public void addSudokuCase(int line, int col, SudokuCase sudokuCase) {
        grid[line][col] = sudokuCase;
    }

    public void removePossibleAnswer(int line, int col, int wrongAnswer) {
        if (grid[line][col].getAnswers() == 0) {
            grid[line][col].removePossibleAnswer(wrongAnswer);
        }
    }

    public List<Integer> intOnLine(int line) {
        List<Integer> intToReturn = new ArrayList<Integer>();
        for (int col = 0; col < 9; col++) {
            int answer = grid[line][col].getAnswers();
            if (answer != 0) {
                intToReturn.add(answer);
            }
        }
        return intToReturn;
    }

    public List<Integer> intOnCol(int col) {
        List<Integer> intToReturn = new ArrayList<Integer>();
        for (int line = 0; line < 9; line++) {
            int answer = grid[line][col].getAnswers();
            if (answer != 0) {
                intToReturn.add(answer);
            }
        }
        return intToReturn;
    }

    public void validateAnswers() throws SolveError {
        for (int line = 0; line < 9; line++) {
            for (int col = 0; col < 9; col++) {
                try {
                    grid[line][col].validateAnswer();
                } catch (SolveError solveError) {
                    throw new SolveError(solveError.getMessage() + " on " + line + ":" + col);
                }
            }
        }
    }

    public List<Integer> intInSquare(int line, int col) {
        int startingLine = 3 * (line / 3);
        int startingColumn = 3 * (col / 3);
        List<Integer> intToReturn = new ArrayList<Integer>();
        for (int l = startingLine; l < startingLine + 3; l++) {
            for (int c = startingColumn; c < startingColumn + 3; c++) {
                if (grid[l][c].getAnswers() != 0) {
                    intToReturn.add(grid[l][c].getAnswers());
                }
            }
        }
        return intToReturn;
    }

    public boolean isGridSolved() {
        for (int line = 0; line < 9; line++) {
            for (int col = 0; col < 9; col++) {
                if (grid[line][col].getAnswers() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                SudokuCase sudokuCase = grid[i][j];
                if (sudokuCase.getAnswers() != 0) {
                    sb.append(sudokuCase.getAnswers());
                } else {
                    sb.append("_");
                }
                if ((j + 1) % 3 == 0) {
                    sb.append("|");
                } else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
            if ((i + 1) % 3 == 0) {
                sb.append("-----------------\n");
            }
        }
        return sb.toString();
    }
}
