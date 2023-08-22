package com.techelevator.QuizzMe.controller;

import com.techelevator.QuizzMe.dao.QuestionDao;
import com.techelevator.QuizzMe.dao.QuizDao;
import com.techelevator.QuizzMe.dao.ResultDao;
import com.techelevator.QuizzMe.dao.UserDao;
import com.techelevator.QuizzMe.model.Question;
import com.techelevator.QuizzMe.model.Quiz;
import com.techelevator.QuizzMe.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
public class QuizMeController {
    @Autowired
    UserDao userdao;
    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;
    @Autowired
    ResultDao resultDao;

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers(){
        return userdao.getUsers();
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable int id){
        return userdao.getUser(id);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/users" , method = RequestMethod.POST)
    public User addUser(@RequestBody User user){
        return userdao.addUser(user);
    }
    @RequestMapping(path = "/users/{id}", method = RequestMethod.PUT)
    public User editUser(@RequestBody User user, @PathVariable int id){
        return userdao.editUser(user,id);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/users/{id}", method = RequestMethod.DELETE)
    public void removeUser(int userId){
        userdao.removeUser(userId);
    }



    @RequestMapping(path = "/quizzes", method = RequestMethod.GET)
    public List<Quiz> getAllQuizzes(){
       return quizDao.getQuizzes();
    }
    @RequestMapping(path = "/quizzes/{id}", method = RequestMethod.GET)
    public Quiz getQuiz(@PathVariable int id){
        return quizDao.getQuiz(id);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/quizzes", method = RequestMethod.POST)
    public Quiz addQuiz(@Valid @RequestBody Quiz quiz){
        return quizDao.addQuiz(quiz);
    }
    @RequestMapping(path = "/quizzes/{id}", method = RequestMethod.PUT)
    public Quiz editQuiz(@Valid @RequestBody Quiz quiz, @PathVariable int id){
        return quizDao.editQuiz(quiz, id);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/quizzes/{id}", method = RequestMethod.DELETE)
    public void removeQuiz(int id){
        quizDao.removeQuiz(id);
    }

    @RequestMapping(path = "/questions", method = RequestMethod.GET)
    public List<Question> getAllQuestions(){
        return questionDao.getQuestions();
    }
    @RequestMapping(path = "/quiz/{id}/questions", method = RequestMethod.GET)
    public List<Question> getQuestionsByQuiz(@PathVariable int id){
        return questionDao.getQuestionsByQuiz(id);
    }
    @RequestMapping(path = "/questions/{id}", method = RequestMethod.GET)
    public Question getQuestionById(@PathVariable int id){
        return questionDao.getQuestion(id);
    }
    @RequestMapping(path = "/questions", method = RequestMethod.POST)
    public Question addQuestion(@Valid @RequestBody Question question){
        if (question.getAnswerTwo()==null){
            return questionDao.addQuestionMath(question);
        }else if(question.getAnswerThree()==null){
            return questionDao.addQuestionTF(question);
        }else{
            return questionDao.addQuestionMultiple(question);
        }
    }
    @RequestMapping(path = "/questions/{id}", method = RequestMethod.PUT)
    public Question editQuestion(@Valid @RequestBody Question question,@PathVariable int id){
        return questionDao.editQuestion(question, id);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/questions/{id}")
    public void removeQuestion(@PathVariable int id){
        questionDao.removeQuestion(id);
    }

    


}
