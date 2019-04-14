package com.example.lin9080.hackthon_lbx;

import java.util.ArrayList;

public class ScoreOfTerm {
    private int term;
    private ArrayList<ScoreOfStu> scoreOfStus;

    public ScoreOfTerm(int term, ArrayList<ScoreOfStu> scoreOfStus) {
        this.term = term;
        this.scoreOfStus = scoreOfStus;
    }

    public int getTerm() {
        return term;
    }

    public void setTerm(int term) {
        this.term = term;
    }

    public ArrayList<ScoreOfStu> getScoreOfStus() {
        return scoreOfStus;
    }

    public void setScoreOfStus(ArrayList<ScoreOfStu> scoreOfStus) {
        this.scoreOfStus = scoreOfStus;
    }
}
