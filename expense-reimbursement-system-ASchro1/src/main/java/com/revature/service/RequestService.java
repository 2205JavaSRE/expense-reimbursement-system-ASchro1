package com.revature.service;

import com.revature.dao.RequestDao;
import com.revature.dao.UserDao;
import com.revature.models.Request;

import java.util.*;

public class RequestService {
    private static RequestDao rDao = new RequestDao();

    private static UserDao uDao = new UserDao();
    public static boolean createRequest(String username, String type, double amount){
        List<String> users = uDao.getUsernames();
        HashSet<String> types = new HashSet<>();
        types.add("LODGING");
        types.add("FOOD");
        types.add("TRAVEL");
        types.add("OTHER");
        if(!users.contains(username) || amount < 0 || !types.contains(type)){
            return false;
        }
        Request r = new Request(-1, username, type, amount, "Pending");
        rDao.insertRequest(r);
        return true;
    }

    public static List<Request> getAllPendingRequests(){
        return rDao.selectAllPendingRequests();
    }

    public static boolean updateRequest(Integer requestId, String status){
        List<Integer> rList = rDao.getRequestIds();
        if(!rList.contains(requestId) || (!status.equals("Approved") && !status.equals("Denied"))){
            return false;
        }
        rDao.updateRequest(requestId, status);
        return true;
    }

    public static List<Request> getAllPastRequests(){
        return rDao.selectAllPastRequests();
    }

    public static List<Request> getAllRequests(){
        return rDao.selectAllRequests();
    }

    public static List<Request> getPendingRequestsByUser(String username){
        List<String> users = uDao.getUsernames();
        if(users.contains(username)) {
            List<Request> rList = rDao.selectRequestsByUsername(username);
            rList.removeIf(r -> (!r.getStatus().equals("Pending")));
            return rList;
        }else{
            return null;
        }

    }

    public static List<Request> getPastRequestsByUser(String username){
        List<String> users = uDao.getUsernames();
        if(users.contains(username)) {
            List<Request> rList = rDao.selectRequestsByUsername(username);
            rList.removeIf(r -> (r.getStatus().equals("Pending")));
            return rList;
        }else{
            return null;
        }

    }



}
