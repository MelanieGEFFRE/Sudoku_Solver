package com.geffre.app;

import com.geffre.error.SolveError;
import com.geffre.model.SudokuCase;
import com.geffre.model.SudokuGrid;
import com.geffre.solver.SudokuSolver;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        AtomicInteger line = new AtomicInteger();
        AtomicInteger col = new AtomicInteger();
        SudokuGrid grid = new SudokuGrid();
        try (Stream<String> stream = Files.lines(Paths.get("src/files/sudoku_to_solve.txt"),
                StandardCharsets.UTF_8)){
            stream.forEach(s -> {
                for (String threeCase : s.split("\\|")) {
                    for (int i = 0; i<3;i++){
                        SudokuCase sudokuCase;
                        if (threeCase.charAt(i) != '_'){
                            sudokuCase = new SudokuCase(Integer.parseInt(String.valueOf(threeCase.charAt(i))));
                        } else {
                            sudokuCase = new SudokuCase();
                        }
                        grid.addSudokuCase(line.intValue(), col.intValue(), sudokuCase);
                        col.getAndIncrement();
                    }
                }
                line.getAndIncrement();
                col.set(0);
            });
            SudokuSolver solver = new SudokuSolver(grid);
            solver.solve();
        }
        catch (IOException | SolveError e)
        {
            e.printStackTrace();
        }
    }
}
