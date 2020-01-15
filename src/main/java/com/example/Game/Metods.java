package com.example.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Metods {

    @Autowired
    userRepository userRepository;
    @Autowired
    SpelRepository spelRepository;


    public UserInfo getUser(String username) {
        return userRepository.getUserByUsername(username);
    }

    public void addUser(UserInfo user) {
        userRepository.addUser(user);
    }


    public void addSpel(Spel spel, int id) {
        spelRepository.addSpel(spel, id);
    }

    public void getGameByGameName(String gamename) {
        spelRepository.getGamesByGameName(gamename);
    }
}



