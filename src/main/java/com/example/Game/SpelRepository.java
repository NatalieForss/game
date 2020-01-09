package com.example.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;
import java.sql.*;
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
        spel.setStatus(rs.getBoolean("Status"));
        spel.setLocation(rs.getString("Location"));
        spel.setTypeOfExchange(rs.getString("typeOfExchange"));
        spel.setCategory(rs.getString("Spelkategori"));
        return spel;
    }

    //lägger till DB
    public void addSpel(Spel spelToAdd) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(
                     "INSERT INTO Spel (GameNamn, Status, Location, TypeOfExchange, Category) " +
                             "VALUES(?, ?, ?, ?, ?, )")) {
            preparedStatement.setString(1, spelToAdd.getName());
            preparedStatement.setBoolean(2, spelToAdd.isStatus());
            preparedStatement.setString(3, spelToAdd.getLocation());
            preparedStatement.setString(4, spelToAdd.getTypeOfExchange());
            preparedStatement.setString(5, spelToAdd.getCategory());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // hämtar fråm DB
    public List<Spel> getSpel(){
        spelList.clear();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM SPEL")){
            while(rs.next()){
                spelList.add(rsSpel(rs));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return spelList;
    }

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
