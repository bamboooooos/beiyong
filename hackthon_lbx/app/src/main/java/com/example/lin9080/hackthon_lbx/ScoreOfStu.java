package com.example.lin9080.hackthon_lbx;

import java.util.ArrayList;

public class ScoreOfStu {
    private String StuID;
    private String StuName;
    private ArrayList<Score> scores;
    private int Sort;

    public ScoreOfStu(String stuID, String stuName, ArrayList<Score> scores, int sort) {
        StuID = stuID;
        StuName = stuName;
        this.scores = scores;
        Sort = sort;
    }

    public String getStuID() {
        return StuID;
    }

    public void setStuID(String stuID) {
        StuID = stuID;
    }

    public String getStuName() {
        return StuName;
    }

    public void setStuName(String stuName) {
        StuName = stuName;
    }

    public ArrayList<Score> getScores() {
        return scores;
    }

    public void setScores(ArrayList<Score> scores) {
        this.scores = scores;
    }

    public int getSort() {
        return Sort;
    }

    public void setSort(int sort) {
        Sort = sort;
    }
}
