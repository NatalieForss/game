package com.example.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SpelRepository {

    @Autowired
    DataSource dataSource;

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

    @GetMapping("/dbtest")
    public boolean testDB() throws SQLException {
        int two = 0;
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT 1+1")) {
            rs.next();
            two = rs.getInt(1);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return two == 2;
    }

}
