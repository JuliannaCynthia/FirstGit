package com.techelevator.QuizzMe.dao;

import com.techelevator.QuizzMe.Exception.DaoException;
import com.techelevator.QuizzMe.model.Quiz;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
@Component
public class JDBCQuizDao implements QuizDao{

    private JdbcTemplate template;

    public JDBCQuizDao(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public Quiz mapRowToQuiz(SqlRowSet rowSet) {

        Quiz quiz = new Quiz();
        quiz.setId(rowSet.getInt("quiz_id"));
        quiz.setName(rowSet.getString("quiz_name"));
        quiz.setNumOfQuestions(rowSet.getInt("num_of_questions"));
        quiz.setType(rowSet.getString("type"));
        quiz.setDifficulty(rowSet.getString("difficulty"));

        return quiz;
    }

    @Override
    public List<Quiz> getQuizzes() {
        String sql = "SELECT * from quiz;";
        List<Quiz> quizzes = new ArrayList<>();
        try {
            SqlRowSet rowSet = template.queryForRowSet(sql);

            while (rowSet.next()) {

                Quiz quiz = mapRowToQuiz(rowSet);
                quizzes.add(quiz);
            }
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot access server.", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }

        return quizzes;
    }

    @Override
    public Quiz getQuiz(int id) {
        Quiz quiz = null;
        String sql = "SELECT * FROM quiz where quiz_id = ?";
        try {
            SqlRowSet rowSet = template.queryForRowSet(sql, id);

            if (rowSet.next()) {
                quiz = mapRowToQuiz(rowSet);
            }
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot access server.", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }
        return quiz;
    }

    @Override
    public Quiz addQuiz(Quiz quiz) {
        int newId;
        String sql = "INSERT INTO quiz(quiz_name,num_of_questions,type,difficulty) VALUES (?, ?, ?, ?) RETURNING id;";
        try {
            newId = template.queryForObject(sql, int.class,
                    quiz.getName(), quiz.getNumOfQuestions(), quiz.getType(), quiz.getDifficulty());
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot access server.", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }
        return getQuiz(newId);
    }

    @Override
    public Quiz editQuiz(Quiz quiz, int quizId) {
        String sql = "update quiz set quiz_name = ?, num_of_questions = ?, type = ?, difficulty = ? where quiz_id = ?;";
        int rowsAffected;
        Quiz updatedQuiz;
        try{
            rowsAffected = template.update(sql, quiz.getName(),quiz.getNumOfQuestions(),quiz.getType(),quiz.getDifficulty(),quizId);
            if(rowsAffected != 1){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            updatedQuiz = getQuiz(quiz.getId());
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot access server.", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }
        return updatedQuiz;
    }

    @Override
    public void removeQuiz(int id) {
        int rowsAffected;
        String resultSql = "delete from result where quiz_id = ?;";
        String sql = "delete from quiz where quiz_id = ?;";
        try{
            template.update(resultSql , id);
            rowsAffected = template.update(sql, id);
            if(rowsAffected != 1){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot access server.", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }
    }
}
