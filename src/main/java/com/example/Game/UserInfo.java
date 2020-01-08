package com.example.Game;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;


public class UserInfo {
    @Autowired
    userRepository userRepository;

        private int id;
    @Size(min=5, max=30)
        private String userName;
    @Size(min=5, max=30)
        private String password;
    @Email
        private String mail;
        private Double rating;
        private boolean isLoggedIn;


    public UserInfo() {

    }

    public UserInfo(int id, String userName, String password, String mail, Double rating, boolean isLoggedIn) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.mail = mail;
        this.rating = rating;
        this.isLoggedIn = isLoggedIn;
    }

    public UserInfo(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UserInfo(String userName, String password, String mail, int userId) {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }


    public Boolean getLoggedIn() {
        return isLoggedIn;
    }

}

