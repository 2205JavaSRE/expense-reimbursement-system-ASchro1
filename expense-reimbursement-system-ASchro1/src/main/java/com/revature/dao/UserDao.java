package com.revature.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

public class UserDao {
    public User selectUserByUsername(String username){
        String sql = "SELECT * FROM project1.users WHERE (username) = (?)";
        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new User(rs.getString("username"),
                    rs.getString("user_auth"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    isManager(rs.getString("username")),
                    null);


        }catch(SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public List<String> getUsernames(){
        String sql = "SELECT username FROM project1.users";
        Connection connection = ConnectionFactory.getConnection();

        List<String> userList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                userList.add(rs.getString("username"));
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }

    public boolean isManager(String username){
        String sql = "SELECT user_id FROM project1.managers";
        Connection connection = ConnectionFactory.getConnection();

        List<String> userList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                userList.add(rs.getString("user_id"));
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }

        return userList.contains(username);

    }
}
