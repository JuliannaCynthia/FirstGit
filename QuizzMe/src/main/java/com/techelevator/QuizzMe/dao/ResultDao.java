package com.techelevator.QuizzMe.dao;

import com.techelevator.QuizzMe.model.Quiz;
import com.techelevator.QuizzMe.model.Result;

import java.util.List;

public interface ResultDao {
    public List<Result> getResults();
    public List<Result> getResultsByUserAndQuiz(int quizId, int userId);
    public List<Result> getResultsByUser(int userId);
    public Result addResult(Result result);
    public Result editResult(Result result, int resultId);
    public void removeResult(int id);
    public void removeResultsByUser(int userId);
    public void removeResultsByQuiz(int quizId);
}
