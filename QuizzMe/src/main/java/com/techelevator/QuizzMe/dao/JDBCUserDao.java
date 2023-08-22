package com.techelevator.QuizzMe.dao;

import com.techelevator.QuizzMe.Exception.DaoException;
import com.techelevator.QuizzMe.model.User;
import org.springframework.dao.DataAccessException;
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
public class JDBCUserDao implements UserDao{
    private JdbcTemplate template;

    public JDBCUserDao(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public User mapRowToUser(SqlRowSet rowSet) {

        User user = new User();
        user.setId(rowSet.getInt("user_id"));
        user.setName(rowSet.getString("name"));
        user.setQuizTakenCount(rowSet.getInt("quiz_taken_count"));
        user.setPassword(rowSet.getString("password"));
        user.setUserEmail(rowSet.getString("user_email"));

        return user;
    }

    public List<User> getUsers() {

        String sql = "SELECT * from users;";
        List<User> users = new ArrayList<>();
        try {
            SqlRowSet rowSet = template.queryForRowSet(sql);

            while (rowSet.next()) {

                User user = mapRowToUser(rowSet);
                users.add(user);
            }
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot access server.", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }

        return users;
    }

    @Override

    public User getUser(int id) {
        User user = null;
        String sql = "SELECT * FROM users where user_id = ?";
        try {
            SqlRowSet rowSet = template.queryForRowSet(sql, id);

            if (rowSet.next()) {
                user = mapRowToUser(rowSet);
            }

        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot access server.", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }
        return user;
    }

    @Override
    public User getUserByUsername(String name) {
        User user = null;
        String sql = "SELECT user_id,name,password from users where name ilike ?";
        try{
            SqlRowSet rowSet = template.queryForRowSet(sql,name);
            if(rowSet.next()){
                user = mapRowToUser(rowSet);
            }
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot access server.", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }
        return user;
    }

    public User addUser(User user) {
        int newId;
        String sql = "INSERT INTO users(name,quiz_taken_count,password,user_email) VALUES (?, ?, ?, ?) RETURNING user_id;";
        try {
            newId = template.queryForObject(sql, int.class,
                    user.getName(), user.getQuizTakenCount(), user.getPassword(), user.getUserEmail());
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot access server.", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }
        return getUser(newId);
    }

    @Override
    public User editUser(User userToEdit, int userId) {
        String sql = "update users set name = ?, quiz_taken_count = ?, password = ?, user_email = ? where user_id = ?;";
        int rowsAffected;
        User user;
        try{
            rowsAffected = template.update(sql, userToEdit.getName(),userToEdit.getQuizTakenCount(),userToEdit.getPassword(),userToEdit.getUserEmail(), userId);
            if(rowsAffected != 1){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
            user = getUser(userToEdit.getId());
        }catch(CannotGetJdbcConnectionException e){
            throw new DaoException("Cannot access server.", e);
        }catch(DataIntegrityViolationException e){
            throw new DaoException("Data Integrity Violation", e);
        }
        return user;
    }

    @Override
    public void removeUser(int id) {
        int rowsAffected;
        String resultSql = "delete from result where user_id = ?;";
        String sql = "delete from user where user_id = ?;";
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
