package com.example.Game;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class userRepository {


        private List<UserInfo> users;
        private List<Integer> userLowScore = new ArrayList<>();

        public userRepository() {
            users = new ArrayList<>();

        }

        public void saveUser(UserInfo userInfo){
            users.add(userInfo);

        }

//        public UserInfo checkLogin (String name, String password) {
//            for (UserInfo uInfo : users) {
//                if (uInfo.getUserName().equalsIgnoreCase(name) && uInfo.getPassword().equalsIgnoreCase(password)) {
//                    uInfo.setLoggedIn(true);
//                    return uInfo;
//                }
//            }
//
//            return null;
//        }

        public void setUserLowScore(int count) {
            userLowScore.add(count);
        }

        public List<Integer> getUserLowScore() {
            return userLowScore;
        }
}
