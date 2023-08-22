package com.techelevator.QuizzMe.dao;

import com.techelevator.QuizzMe.model.User;

import java.util.List;

public interface UserDao {
    public List<User> getUsers();
    public User getUser(int id);
    public User getUserByUsername(String name);
    public User addUser(User user);
    public User editUser(User user, int userId);
    public void removeUser(int id);
}
