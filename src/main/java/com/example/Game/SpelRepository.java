package com.example.Game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Repository
public class SpelRepository {


    @Autowired
    DataSource dataSource;

    //instance variables
  //  private List<Spel> spelList;

    private Spel spel = null;


    public SpelRepository() {
       // spelList = new ArrayList<>();
        List <Spel>spelList = new ArrayList<>();
    }

    public List<Spel> getGamesByCategory(String category) {
        //spelList.clear();
       List <Spel>spelList = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Game WHERE Category=" + category)){
            while(rs.next()){
                Spel spel = new Spel();
                spel.setCategory(rs.getString("Category"));
                spel.setLocation(rs.getString("Location"));
                spel.setName(rs.getString("GameName"));
                spel.setTypeOfExchange(rs.getString("TypeOfExchange"));
                spel.setUserID(rs.getString("UserId"));
                spelList.add(spel);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return spelList;
    }

    public List<Spel> getGamesByGameName(String name) {
        //spelList.clear();
        List <Spel>spelList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Game WHERE GameName='" + name+"'")){
            while(rs.next()){
                Spel spel = new Spel();
                spel.setName(rs.getString("GameName"));
                spel.setLocation(rs.getString("Location"));
                spel.setTypeOfExchange(rs.getString("TypeOfExchange"));
                spel.setUserID(rs.getString("UserId"));

                spelList.add(spel);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return spelList;
    }

    public List<SearchResult> getGameOwner(List<Spel> spel) {
        List<SearchResult> searchResults = new ArrayList<>();
        for (Spel s: spel) {
            try (Connection conn = dataSource.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM [User] WHERE UserId='" + s.getUserID() + "'")) {
                while (rs.next()) {
                    UserInfo user = new UserInfo();
                    user.setMail(rs.getString("Mail"));
                    user.setUserName(rs.getString("UserName"));
                    SearchResult result = new SearchResult();
                    result.setGameName(s.getName());
                    result.setLocation(s.getLocation());
                    result.setTypeOfExchange(s.getTypeOfExchange());
                    result.setUserInfo(user);
                    searchResults.add(result);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return searchResults;
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
       // spelList.clear();
        List <Spel>spelList = new ArrayList<>();
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


    public List<Spel> getPage(int page, int pageSize, String category, String id ) {
       List<Spel> spelList = getGamesByCategory(category);
        //List <Spel>spelList = new ArrayList<>();

            int from = Math.max(0, page * pageSize);
        int to = Math.min(spelList.size(), (page + 1) * pageSize);

        return spelList.subList(from, to);

    }

    public int numberOfPages(int pageSize, String category, String id) {
        List<Spel> spelList = getGamesByCategory(category);

        return (int)Math.ceil(new Double(spelList.size()) / pageSize);
    }









//    public Spel getGameByGamename(String gamename){
//
//        try (Connection conn = dataSource.getConnection();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery("SELECT * FROM Game WHERE GameName='" + gamename + "'")){
//            if(rs.next()){
//                return rsSpel(rs);
//            }
//        }
//        catch(SQLException e){
//            e.printStackTrace();
//        }
//        return null;
//    }
//

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
