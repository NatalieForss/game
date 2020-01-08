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
    public Spel getSpel(int id) {
        for (Spel spel : spelList) {
            if (spel.getId() == id) {
                return spel;
            }
        }
        return null;
    }


    public Spel rsSpel(ResultSet rs) throws SQLException {
        Spel spel = new Spel();
        spel.setId(rs.getInt("SpelId"));
        spel.setName(rs.getString("Spel namn"));
        spel.setCategory(rs.getString("Spelkategori"));
        spel.setStatus(rs.getBoolean("Status"));
        spel.setLocation(rs.getString("Location"));
        return spel;
    }


  /*  //getters and setters
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
    }*/





        @GetMapping("/dbtest")
        public boolean testDB () throws SQLException {
            int two = 0;
            try (Connection conn = dataSource.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT 1+1")) {
                rs.next();
                two = rs.getInt(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return two == 2;
        }

    }
