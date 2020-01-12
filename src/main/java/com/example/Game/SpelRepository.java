package com.example.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Repository
public class SpelRepository {

    @Autowired
    DataSource dataSource;

    //instance variables
    private List<Spel> spelList;


    private Spel spel = null;


    public SpelRepository() {
        spelList = new ArrayList<>();
    }

    public List<Spel> getGamesByCategory(String category) {
        spelList.clear();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Game WHERE Category=" + category)){
            while(rs.next()){
                Spel spel = new Spel();
                spel.setCategory(rs.getString("Category"));
                spel.setLocation(rs.getString("Location"));
                spel.setName(rs.getString("GameName"));
                spel.setTypeOfExchange(rs.getString("TypeOfExchange"));
                System.out.println(spel.getCategory());
                spelList.add(spel);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return spelList;
    }




    public Spel rsSpel(ResultSet rs) throws SQLException {
        Spel spel = new Spel();
        spel.setId(rs.getInt("GameId"));
        spel.setName(rs.getString("GameName"));
        spel.setStatus(rs.getBoolean("Status"));
        spel.setLocation(rs.getString("Location"));
        spel.setTypeOfExchange(rs.getString("TypeOfExchange"));
        spel.setCategory(rs.getString("Category"));

        return spel;
    }

    //lägger till DB
    public void addSpel(Spel spelToAdd, int id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(
                     "INSERT INTO Game (GameName, Status, Location, TypeOfExchange, Category, UserId) VALUES(?, ?, ?, ?, ?, ? )")) {
            preparedStatement.setString(1, spelToAdd.getName());
            preparedStatement.setBoolean(2, spelToAdd.isStatus());
            preparedStatement.setString(3, spelToAdd.getLocation());
            preparedStatement.setString(4, spelToAdd.getTypeOfExchange());
            preparedStatement.setString(5, spelToAdd.getCategory());
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // hämtar fråm DB
    public List<Spel> getGame(){
        spelList.clear();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Game")){
            while(rs.next()){
                System.out.println(rs.getString("GameName"));
                spelList.add(rsSpel(rs));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return spelList;
    }

    public Spel getGameByGamename(String gamename){

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Game WHERE GameName='" + gamename + "'")){
            if(rs.next()){
                return rsSpel(rs);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //retunerar lisa med spel sorterade på tillänlighet
    public List<Spel> getSortedSpelList(int pageNr, int itemsPerPage, boolean onlyAvailable) {

        List<Spel> subList = new ArrayList<Spel>();

        //Collections.sort(spelList);

        //antal spel som visas på hemsida är begränsad
        for(int ii=0; ii< Math.min(itemsPerPage, spelList.size()); ii++){

            Spel spel = spelList.get(pageNr+ii);


            if(onlyAvailable){// om man krysar in härr så kommer att visas endast  tillgänliga spel
                if(spel.isStatus()){
                    subList.add(spel);
                }
            }
            else {
                subList.add(spel);
            }
        }
        return subList;
    }

    public Spel getGameByBarnspelCategory(String gameCategory){

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Game WHERE Category LIKE Barnspel")){
            if(rs.next()){
                return rsSpel(rs);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return null;
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
