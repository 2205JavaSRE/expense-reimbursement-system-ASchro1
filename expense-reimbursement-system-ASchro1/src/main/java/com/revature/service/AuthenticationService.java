package com.revature.service;

import com.revature.dao.UserDao;
import com.revature.models.User;

import java.util.*;

public class AuthenticationService {
    public static boolean authenticateUser(String username, String pass){
        UserDao uDao = new UserDao();
        List<String> userList = uDao.getUsernames();
        if(!userList.contains(username)){
            return false;
        }else {
            User u = uDao.selectUserByUsername(username);
            return u.getPassword().equals(pass);
        }
    }
}
