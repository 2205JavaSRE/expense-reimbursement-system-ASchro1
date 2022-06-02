package com.revature.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import com.revature.models.Request;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

public class RequestDao {

    public boolean insertRequest(Request r){
        String sql = "INSERT INTO project1.request (user_id, r_type, amount, status) VALUES (?, ?, ?, ?)";
        Connection connection = ConnectionFactory.getConnection();

        try(PreparedStatement ps = connection.prepareStatement(sql)){ //connection will be closed after we are done!

            ps.setString(1, r.getUserId());
            ps.setString(2, r.getType());
            ps.setDouble(3, r.getAmount());
            ps.setString(4, r.getStatus());


            ps.execute(); //We use execute when we DONT expect anything back
            //ps.executeQuery(); //WE use use we DO expect something back!
            return true;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Request> selectAllPendingRequests(){
        List<Request> rList = new ArrayList<>();
        String sql = "SELECT * FROM project1.request WHERE r_status = ?";
        Connection connection = ConnectionFactory.getConnection();

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, "Pending");
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                Request t = new Request(rs.getInt("request_id"),
                        rs.getString("user_id"),
                        rs.getString("r_type"),,
                        rs.getDouble("amount"),
                        rs.getString("r_status"));

                rList.add(t);

            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return rList;
    }

    public List<Request> selectRequestsByUsername(String username){
        List<Request> rList = new ArrayList<>();
        String sql = "SELECT * FROM project1.request WHERE user_id = ?";
        Connection connection = ConnectionFactory.getConnection();

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                Request t = new Request(rs.getInt("request_id"),
                        rs.getString("user_id"),
                        rs.getString("r_type"),,
                        rs.getDouble("amount"),
                        rs.getString("r_status"));

                rList.add(t);

            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return rList;
    }

    public Request getRequestById(int requestId){
        String sql = "SELECT * FROM project1.users WHERE (request_id) = (?)";
        Connection connection = ConnectionFactory.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, requestId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new Request(rs.getInt("request_id"),
                    rs.getString("user_id"),
                    rs.getString("r_type"),,
                    rs.getDouble("amount"),
                    rs.getString("r_status"));


        }catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public void updateRequest(Request r){
            String sql = "UPDATE project1.request SET r_status = ? WHERE r_id = ?";
            Connection connection = ConnectionFactory.getConnection();

            try(PreparedStatement ps = connection.prepareStatement(sql)){
                ps.setString(1, r.getStatus());
                ps.setInt(2, r.getRequestId());
                ps.executeUpdate();

            }catch(SQLException e) {
                e.printStackTrace();
            }


    }

}
