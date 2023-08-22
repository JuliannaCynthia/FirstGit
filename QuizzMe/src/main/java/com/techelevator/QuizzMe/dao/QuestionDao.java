package com.techelevator.QuizzMe.dao;

import com.techelevator.QuizzMe.model.Question;
import com.techelevator.QuizzMe.model.User;

import java.util.List;

public interface QuestionDao {
    public List<Question> getQuestions();
    public Question getQuestion(int id);
    public Question addQuestionMultiple(Question question);
    public Question addQuestionMath(Question question);
    public Question addQuestionTF(Question question);
    public Question editQuestion(Question question, int id);
    public void removeQuestion(int id);
    public List<Question> getQuestionsByQuiz(int quizId);
}
