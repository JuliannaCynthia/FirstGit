package com.techelevator.QuizzMe.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Quiz {
    private int id;
    @NotBlank
    private String name;
    @Min(value = 1,message = "Quizzes cannot have less than one question.")
    private int numOfQuestions;
    @NotBlank
    private String type;
    private String difficulty;

    public Quiz(int id, String name, int numOfQuestions, String type, String difficulty) {
        this.id = id;
        this.name = name;
        this.numOfQuestions = numOfQuestions;
        this.type = type;
        this.difficulty = difficulty;
    }

    public Quiz() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfQuestions() {
        return numOfQuestions;
    }

    public void setNumOfQuestions(int numOfQuestions) {
        this.numOfQuestions = numOfQuestions;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
