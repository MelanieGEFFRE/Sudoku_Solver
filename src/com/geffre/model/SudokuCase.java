package com.geffre.model;

import com.geffre.error.SolveError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SudokuCase {

    private Map<Integer, Boolean> possibleFills;
    private int answers;

    public SudokuCase (){
        possibleFills = allPossibleFills();
        answers = 0;
    }

    public SudokuCase (int finalAnswer){
        answers = finalAnswer;
        possibleFills = onePossibleFill(finalAnswer);
    }

    private Map<Integer, Boolean> allPossibleFills() {
        Map<Integer, Boolean> mapToReturn = new HashMap<Integer, Boolean>();
        for (int i = 1; i<=9;i++){
            mapToReturn.put(i, true);
        }
        return mapToReturn;
    }

    private Map<Integer, Boolean> onePossibleFill(int finalAnswer) {
        Map<Integer, Boolean> mapToReturn = new HashMap<Integer, Boolean>();
        for (int i = 1; i<=9;i++){
            mapToReturn.put(i, i==finalAnswer);
        }
        return mapToReturn;
    }

    public Map<Integer, Boolean> getPossibleFills() {
        return possibleFills;
    }

    public void setPossibleFills(List<Integer> listImpossible) {
        for (Integer wrongAnswer : listImpossible){
            this.possibleFills.put(wrongAnswer, false);
        }
    }

    public int getAnswers() {
        return answers;
    }

    public void setAnswers(int answers) {
        this.answers = answers;
    }

    public void removePossibleAnswer(int wrongAnswer) {
        this.possibleFills.put(wrongAnswer, false);
    }

    public void validateAnswer() throws SolveError {
        List<Integer> possibleAnswers = new ArrayList<>();
        for (Integer possibleAnswer : possibleFills.keySet()){
            if (possibleFills.get(possibleAnswer)){
                possibleAnswers.add(possibleAnswer);
            }
        }
        if (possibleAnswers.size()==1){
            this.setAnswers(possibleAnswers.get(0));
        }
        if (possibleAnswers.size()==0){
            throw new SolveError("No solution available on this cell.");
        }
    }
}
