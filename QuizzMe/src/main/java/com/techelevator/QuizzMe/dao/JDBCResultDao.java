package com.techelevator.QuizzMe.dao;

import com.techelevator.QuizzMe.Exception.DaoException;
import com.techelevator.QuizzMe.model.Question;
import com.techelevator.QuizzMe.model.Result;
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
public class JDBCResultDao implements ResultDao{
    private JdbcTemplate template;

    public JDBCResultDao(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public Result mapRowToResult(SqlRowSet rowSet) {
        Result result = new Result();
        result.setResultId(rowSet.getInt("result_id"));
        result.setQuizId(rowSet.getInt("quiz_id"));
        result.setUserId(rowSet.getInt("user_id"));
        result.setScore(rowSet.getInt("score"));
        result.setTimestamp(rowSet.getDate("quiz_timestamp").toLocalDate());
        return result;
    }

    public List<Result> getResults() {
        String sql = "SELECT * from result;";
        List<Result> results = new ArrayList<>();
        try {
            SqlRowSet rowSet = template.queryForRowSet(sql);

            while (rowSet.next()) {

                Result result = mapRowToResult(rowSet);
                results.add(result);
            }
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot access server.", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }
        return results;
    }

   public List<Result> getResultsByUser(int userId){
       String sql = "SELECT * from result WHERE user_id = ?;";
       List<Result> results = new ArrayList<>();
       try {
           SqlRowSet rowSet = template.queryForRowSet(sql, userId);

           while (rowSet.next()) {

               Result result = mapRowToResult(rowSet);
               results.add(result);
           }
       }catch(CannotGetJdbcConnectionException e){
           throw new DaoException("Cannot access server.", e);
       }catch(DataIntegrityViolationException e){
           throw new DaoException("Data Integrity Violation", e);
       }
       return results;

   }

    public List<Result> getResultsByUserAndQuiz(int userId, int quizId){
        String sql = "SELECT * from result WHERE user_id = ? AND quiz_id = ?;";
        List<Result> results = new ArrayList<>();
        try {
            SqlRowSet rowSet = template.queryForRowSet(sql, userId, quizId);

            while (rowSet.next()) {

                Result result = mapRowToResult(rowSet);
                results.add(result);
            }
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot access server.", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }
        return results;

    }

    public Result getResultByID(int id){
        Result result = null;
        String sql = "SELECT * FROM result where result_id = ?";
        try {
            SqlRowSet rowSet = template.queryForRowSet(sql, id);
            if (rowSet.next()) {
                result = mapRowToResult(rowSet);
            }
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot access server.", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }
        return result;
    }

    public Result addResult(Result result){
        Result newResult = null;
        String sql = "update result set quiz_id = ?, user_id = , score = ?, quiz_timestamp = ? where result_id =?;";
        try{
            SqlRowSet rowSet = template.queryForRowSet(sql,result.getQuizId(),result.getUserId(),result.getScore(),result.getTimestamp(),result.getResultId());
            if(rowSet.next()){
                newResult = mapRowToResult(rowSet);
            }
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot access server.", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }
        return newResult;
    }

    public Result editResult(Result result, int resultId){
        Result updatedResult= result;
        int rowsAffected = 0;
        String sql = "update result set quiz_id = ?, user_id = ?, score = ?, quiz-timestamp = ? where result_id = ?;";
        try{
            rowsAffected = template.update(sql,resultId);
            if(rowsAffected != 1){
                throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
            }else{
                updatedResult= getResultByID(result.getResultId());
                return updatedResult;
            }
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot access server.", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }

    }

    public void removeResult(int resultId){
        int rowsAffected = 0;
        String sql = "delete from result where result_id = ?;";
        try{
            rowsAffected = template.update(sql,resultId);
            if(rowsAffected != 1){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot access server.", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }

    }

    public void removeResultsByUser(int userId){
       String sqlSearch = "select count() from result where user_id =?;";
       String sql = "delete from result where user_id = ?;";
       int sub = 0;
       int bus = 0;
        try{
           SqlRowSet result = template.queryForRowSet(sqlSearch,userId);
           while (result.next()){
               sub++;
           }
           bus = template.update(sql,userId);
           if(bus != sub){
               throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
           }
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot access server.", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }
    }

    public void removeResultsByQuiz(int quizId) {
        String sqlSearch = "select count() from result where quiz_id =?;";
        String sql = "delete from result where quiz_id = ?;";
        int sub = 0;
        int bus = 0;
        try {
            SqlRowSet result = template.queryForRowSet(sqlSearch, quizId);
            while (result.next()) {
                sub++;
            }
            bus = template.update(sql, quizId);
            if (bus != sub) {
                throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED);
            }
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot access server.", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }
    }


}
