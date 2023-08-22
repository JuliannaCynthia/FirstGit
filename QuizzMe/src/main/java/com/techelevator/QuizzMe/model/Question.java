package com.techelevator.QuizzMe.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Question {
    private int id;
    @NotBlank
    private String question;
    @NotBlank
    private String answerOne;
    private String answerTwo;
    private String answerThree;
    private String answerFour;
    @Min(value =1, message = "Correct Choice MUST be (1), 2, 3, or 4.")
    @Max(value =4, message = "Correct Choice MUST be 1, 2, 3, or (4).")
    private int correctAnswerChoice;

    public Question(int id, String question, String answerOne, String answerTwo, String answerThree, String answerFour, int correctAnswerChoice) {
        this.id = id;
        this.question = question;
        this.answerOne = answerOne;
        this.answerTwo = answerTwo;
        this.answerThree = answerThree;
        this.answerFour = answerFour;
        this.correctAnswerChoice = correctAnswerChoice;
    }

    public Question() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(String answerOne) {
        this.answerOne = answerOne;
    }

    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo;
    }

    public String getAnswerThree() {
        return answerThree;
    }

    public void setAnswerThree(String answerThree) {
        this.answerThree = answerThree;
    }

    public String getAnswerFour() {
        return answerFour;
    }

    public void setAnswerFour(String answerFour) {
        this.answerFour = answerFour;
    }

    public int getCorrectAnswerChoice() {
        return correctAnswerChoice;
    }

    public void setCorrectAnswerChoice(int correctAnswerChoice) {
        this.correctAnswerChoice = correctAnswerChoice;
    }
}
