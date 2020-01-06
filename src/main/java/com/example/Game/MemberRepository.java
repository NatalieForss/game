package com.example.Game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


    @Service
    public class MemberRepository {

        @Autowired
        DataSource dataSource;

        //instance variables
        private List<Member> members;

        //constructor
        public MemberRepository () {
            members = new ArrayList<>();
        }

        //helper function to map field namnes to variable anvandarNamn
        public Member rsMember(ResultSet rs) throws SQLException {
            Member member = new Member();
            member.setId(rs.getInt("MedlemsId"));
            member.setAnvandarNamn(rs.getString("Anvandarnamn"));
            member.setLosenord(rs.getString("Losenord"));
            member.setEmailAdress(rs.getString("EmailAdress"));

            return member;
        }

        //add member to database
        public void addMember(Member memberToAdd) {
            try (Connection conn = dataSource.getConnection();
                 PreparedStatement ps = conn.prepareStatement("INSERT INTO Medlem(AnvandarNamn, Losenord, EmailAdress) VALUES(?, ?, ?)")) {
                ps.setString(1, memberToAdd.getAnvandarNamn());
                ps.setString(2, memberToAdd.getLosenord());
                ps.setString(3, memberToAdd.getEmailAdress());
                ps.executeUpdate();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }

        //get members from database
        public List<Member> getMembers(){
            members.clear();
            try (Connection conn = dataSource.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM MEDLEM")){
                while(rs.next()){
                    members.add(rsMember(rs));
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            return members;
        }


        //get member by username from database
        public Member getMemberByUsername(String username){

            try (Connection conn = dataSource.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM MEDLEM WHERE Anvandarnamn='" + username + "'")){
                if(rs.next()){
                    return rsMember(rs);
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
            return null;
        }

    }

