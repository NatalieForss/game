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

    //hämtar spel from DB och sorterar dem i SpelRepository

    public List<Spel> getSortedSpelList(int pageNr, int itemsPerPage, boolean onlyAvailable) {

        spelRepository.getGame();

        return spelRepository.getSortedSpelList(pageNr, itemsPerPage, onlyAvailable);
    }

    public void getGameByGameName(String gamename) {
        spelRepository.getGamesByGameName(gamename);
    }
}



