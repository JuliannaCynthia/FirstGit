package com.techelevator.QuizzMe.dao;

import com.techelevator.QuizzMe.model.Quiz;

import java.util.List;

public interface QuizDao {
    public List<Quiz> getQuizzes();
    public Quiz getQuiz(int id);
    public Quiz addQuiz(Quiz quiz);
    public Quiz editQuiz(Quiz quiz, int quizId);
    public void removeQuiz(int id);

}
