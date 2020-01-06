package com.example.Game;

import java.util.List;

public class UserInfo {

        private String userName;
        private String password;
        private String mail;
        private Boolean isLoggedIn;
        private int lowScore;

        public UserInfo(String userName, String password, String mail, Boolean isLoggedIn) {
            this.userName = userName;
            this.password = password;
            this.mail = mail;
            this.isLoggedIn = isLoggedIn;
            this.lowScore = 0;
        }

        public UserInfo(String userName, String password) {
            this.userName = userName;
            this.password = password;
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

        public Boolean getLoggedIn() {
            return isLoggedIn;
        }

        public void setLoggedIn(Boolean loggedIn) {
            isLoggedIn = loggedIn;
        }

    }

