package com.example.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Metods {

    @Autowired
    userRepository userRepository;
    @Autowired
   SpelRepository spelRepository;


    public UserInfo getUser(String username){
        return userRepository.getUserByUsername(username);
    }

    public void addUser(UserInfo user){
        userRepository.addUser(user);
    }


/* public Spel getSpel(Integer id) {
        return SpelRepository.getSpel(id);
    }*/

    public void addSpel(Spel spel) {
       spelRepository.addSpel(spel);
    }


}
