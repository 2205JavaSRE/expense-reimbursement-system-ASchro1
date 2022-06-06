package com.revature.service;

import com.revature.dao.RequestDao;
import com.revature.dao.UserDao;
import com.revature.models.Request;
import com.revature.models.User;

import java.util.*;

public class UserService {
    public static UserDao uDao = new UserDao();

    public static RequestDao rDao = new RequestDao();

    public static User getUser(String username){
        List<String> users = uDao.getUsernames();
        if(users.contains(username)) {
            User u = uDao.selectUserByUsername(username);
            List<Request> rList = rDao.selectRequestsByUsername(username);
            u.setRequests(rList);
            u.setManager(uDao.isManager(username));
            return u;
        }else{
            return null;
        }
    }

}
