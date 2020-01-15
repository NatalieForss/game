package com.example.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class userRepository {

    @Autowired
    DataSource dataSource;


    private List<UserInfo> users;



    public userRepository() {
        users = new ArrayList<>();

    }

    public UserInfo rsUser(ResultSet rs) throws SQLException{
        UserInfo user = new UserInfo();
        user.setId(rs.getInt("UserId"));
        user.setUserName(rs.getString("UserName"));
        user.setPassword(rs.getString("Password"));
        user.setMail(rs.getString("Mail"));

        return user;
    }

    //add user to database
    public void addUser(UserInfo userInfo) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO [User](UserName, Password, Mail) VALUES(?,?,?)")) {
            System.out.println(userInfo.getUserName());
            ps.setString(1, userInfo.getUserName());
            ps.setString(2, userInfo.getPassword());
            ps.setString(3, userInfo.getMail());
//            ps.setDouble(4, userInfo.getRating());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //get user from database
    public List<UserInfo> getUsers(){
        users.clear();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM [User]")){
            while(rs.next()){
                users.add(rsUser(rs));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return users;
    }

    //get user by username from database
    public UserInfo getUserByUsername(String username){

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM [User] WHERE UserName='" + username + "'")){
            //ResultSet rs = stmt.executeQuery("SELECT * FROM [User] WHERE UserName = 'AGA'")){
            if(rs.next()){
                return rsUser(rs);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public UserInfo ifUserIsLoggedIn(String name, String password) {
        UserInfo user = null;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM [User] WHERE UserName=? AND Password=?")) {
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new UserInfo();
                user.setUserName(rs.getString("UserName"));
                user.setPassword(rs.getString("Password"));
                user.setMail(rs.getString("Mail"));
                user.setLoggedIn(false);
                user.setId(rs.getInt("UserId"));

                if (name.equals(user.getUserName()) && password.equals(user.getPassword())) {
                    user.setLoggedIn(true);
                }
            }
            return user;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
