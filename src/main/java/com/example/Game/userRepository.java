package com.example.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class userRepository {

    @Autowired
    DataSource dataSource;

    private List<UserInfo> users;


    public userRepository() {
        users = new ArrayList<>();

    }

    public void saveUser(UserInfo userInfo) {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Player(Username, Password, Email) VALUES(?,?,?)")) {
            ps.setString(1, userInfo.getUserName());
            ps.setString(2, userInfo.getPassword());
            ps.setString(3, userInfo.getMail());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public UserInfo checkLogin(String name, String password) {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM Player WHERE Username=? AND Password=?")) {
            ps.setString(1, name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                player = new UserInfo(rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        false,
                        rs.getInt("playerId"));

                System.out.println(player.getUserName() + " " + player.getPassword() + " " + player.isLoggedIn());

                if (name.equals(player.getUserName()) && password.equals(player.getPassword())) {

                    player.setLoggedIn(true);
                    System.out.println("Kom in i IF-satsen");
                }
                System.out.println(player.getUserName() + player.isLoggedIn());
            }
            return player;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
