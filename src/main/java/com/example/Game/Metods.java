package com.example.Game;

import org.springframework.stereotype.Service;

@Service
public class Metods {

    userRepository userRepository;

    public UserInfo getUser(String username){
        return userRepository.getUserByUsername(username);
    }

    public void addUser(UserInfo user){
        userRepository.addUser(user);
    }

}
