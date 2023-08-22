package com.techelevator.QuizzMe.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User {
    private int id;
    @NotBlank
    private String name;
    @Min(value = 0, message = "You cannot owe quizzes. Minimum value is 0.")
    private int quizTakenCount;
    @NotBlank
    private String password;
    private String userEmail;
    private boolean activated;
    private Set<Authority> authorities = new HashSet<>();

    public User(int id, String name, String password, String authorities) {

        this.id = id;
        this.name = name;
        this.password = password;
        this.activated = true;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuizTakenCount() {
        return quizTakenCount;
    }

    public void setQuizTakenCount(int quizTakenCount) {
        this.quizTakenCount = quizTakenCount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public void setAuthorities(String authorities) {
        String[] roles = authorities.split(",");
        for(String role : roles) {
            this.authorities.add(new Authority("ROLE_" + role));
        }
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                activated == user.activated &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(authorities, user.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, password, activated, authorities);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + name + '\'' +
                ", activated=" + activated +
                ", authorities=" + authorities +
                '}';
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

}
