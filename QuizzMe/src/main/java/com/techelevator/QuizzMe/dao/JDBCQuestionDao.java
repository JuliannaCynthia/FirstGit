package com.techelevator.QuizzMe.dao;

import com.techelevator.QuizzMe.Exception.DaoException;
import com.techelevator.QuizzMe.model.Question;
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
public class JDBCQuestionDao implements QuestionDao{

    private JdbcTemplate template;

    public JDBCQuestionDao(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public Question mapRowToQuestion(SqlRowSet rowSet) {

        Question question = new Question();
        question.setId(rowSet.getInt("question_id"));
        question.setQuestion(rowSet.getString("question"));
        question.setAnswerOne(rowSet.getString("answer_choice_one"));
        question.setAnswerTwo(rowSet.getString("answer_choice_two"));
        question.setAnswerThree(rowSet.getString("answer_choice_three"));
        question.setAnswerFour(rowSet.getString("answer_choice_four"));
        question.setCorrectAnswerChoice(rowSet.getInt("correct_answer_choice"));

        return question;
    }

    @Override
    public List<Question> getQuestions() {
        String sql = "SELECT * from question;";
        List<Question> questions = new ArrayList<>();
        try {
            SqlRowSet rowSet = template.queryForRowSet(sql);

            while (rowSet.next()) {

                Question question = mapRowToQuestion(rowSet);
                questions.add(question);
            }
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot access server.", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }
        return questions;
    }



    @Override
    public Question getQuestion(int id) {
        Question question = null;
        String sql = "SELECT * FROM question where question_id = ?";
        try {
            SqlRowSet rowSet = template.queryForRowSet(sql, id);
            if (rowSet.next()) {
                question = mapRowToQuestion(rowSet);
            }
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot access server.", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }
        return question;
    }

    @Override
    public Question addQuestionMultiple(Question question) {
        int newId;
        String sql = "INSERT INTO question(question,answer_choice_one,answer_choice_two,answer_choice_three,answer_choice_four,correct_answer_choice) VALUES (?, ?, ?, ?, ?, ?) RETURNING id;";
        try {
            newId = template.queryForObject(sql, int.class,
                    question.getQuestion(), question.getAnswerOne(), question.getAnswerTwo(), question.getAnswerThree(), question.getAnswerFour(), question.getCorrectAnswerChoice());
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot access server.", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }
        return getQuestion(newId);
    }

    @Override
    public Question addQuestionMath(Question question) {
        int newId;
        if(question.getCorrectAnswerChoice() != 1){
            throw new ResponseStatusException(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
        }
        String sql = "INSERT INTO question(question,answer_choice_one,correct_answer_choice) VALUES (?, ?, ?) RETURNING id;";
        try {
            newId = template.queryForObject(sql, int.class, question.getQuestion(), question.getAnswerOne(), question.getCorrectAnswerChoice());
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot access server.", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }
        return getQuestion(newId);
    }

    @Override
    public Question addQuestionTF(Question question) {
        int newId;
        String sql = "INSERT INTO question(question,answer_choice_one,answer_choice_two,correct_answer_choice) VALUES (?, ?, ?, ?) RETURNING id;";
        if(!question.getAnswerOne().equalsIgnoreCase("true") && !question.getAnswerTwo().equalsIgnoreCase("false")){
            throw new DaoException("T/F Quizzes can only have T/F answer choices");
        }
        try {
            newId = template.queryForObject(sql, int.class, question.getQuestion(), question.getAnswerOne().toUpperCase(), question.getAnswerTwo().toUpperCase(), question.getCorrectAnswerChoice());
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot access server.", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }
        return getQuestion(newId);
    }

    @Override
    public Question editQuestion(Question question, int id) {
        String sql = "update question set question = ?, answer_choice_one = ?, answer_choice_two = ?, answer_choice_three = ?, answer_choice_four = ?, correct_answer_choice = ? where question_id = ?;";
        int rowsAffected;
        Question updatedQuestion;
        try{
            rowsAffected = template.update(sql, question.getQuestion(),question.getAnswerOne(),question.getAnswerTwo(),question.getAnswerThree(),question.getAnswerFour(),question.getCorrectAnswerChoice(), id);
            if(rowsAffected != 1){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            updatedQuestion = getQuestion(id);
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot access server.", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }
        return updatedQuestion;
    }

    @Override
    public void removeQuestion(int id) {
        int rowsAffected;
        String resultSql = "delete from quiz_question where question_id = ?;";
        String sql = "delete from question where question_id = ?;";
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

    @Override
    public List<Question> getQuestionsByQuiz(int quizId) {
        List<Question> questions = null;
        String sql = "SELECT * FROM question join quiz_question on question.question_id = quiz_question.question_id where quiz_id = ?;";
        try {
            questions = new ArrayList<>();
            SqlRowSet rowSet = template.queryForRowSet(sql, quizId);
            while (rowSet.next()) {
                Question question = mapRowToQuestion(rowSet);
                questions.add(question);
            }
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot access server.", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }
        return questions;
    }
}
