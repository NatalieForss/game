package com.example.Game;

import org.springframework.stereotype.Service;

@Service
public class Metods {

    userRepository userRepository;
   // SpelRepository spelRepository;


    public UserInfo getUser(String username){
        return userRepository.getUserByUsername(username);
    }

    public void addUser(UserInfo user){
        userRepository.addUser(user);
    }


/*    public Spel getSpel(Integer id) {
        return SpelRepository.getSpel(id);
    }

    public void addSpel(Spel spel) {
       SpelRepository.addSpel(spel);
    }*/


}
