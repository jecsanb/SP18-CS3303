package com.example.jecsan.geoquizapp;

/**
 * Created by jecsan on 1/27/18.
 */

public class Question {
    private String statement;
    private boolean answer;

    Question(String statement, boolean answer) {
        this.statement = statement;
        this.answer = answer;
    }

    public String getStatement() {
        return this.statement;
    }

    public boolean getAsnwer() {
        return this.answer;
    }

}
