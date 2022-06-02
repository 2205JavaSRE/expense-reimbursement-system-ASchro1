package com.revature.service;

import com.revature.dao.RequestDao;
import com.revature.dao.UserDao;
import com.revature.models.Request;
import com.revature.models.User;

import java.util.*;

public class UserService {
    public static UserDao uDao = new UserDao();

    public static RequestDao rDao = new RequestDao();

    public User getUser(String username){
        User u = uDao.selectUserByUsername(username);
        List<Request> rList = rDao.selectRequestsByUsername(username);
        u.setRequests(rList);
        return u;
    }

}
