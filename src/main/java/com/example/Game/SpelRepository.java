package com.example.Game;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SpelRepository {


    //instance variables
    private List<Spel> spelList;

    //constructor
    public SpelRepository() {
        spelList = new ArrayList<Spel>();
    }

    //getters and setters
    public List<Spel> getSpelList() {
        return spelList;
    }

    public Spel getSpel(int id) {
        for (Spel spel : spelList) {
            if (spel.getId() == id) {
                return spel;
            }
        }
        return null;
    }

    public void create5FakeSpel(){

    for (int i = 0; i < 5; i++) {


        Spel newSpel = new Spel("Spel " +i);
            newSpel.setId(i);
            addSpel(newSpel);
        }
    }

    private void addSpel(Spel spelToAdd) {
        spelList.add(spelToAdd);
    }
}
