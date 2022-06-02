package com.revature;
import com.revature.dao.UserDao;
import com.revature.models.User;
import com.revature.service.AuthenticationService;
import io.javalin.Javalin;
public class MainDriver {
    public static void main(String[] args){
        UserDao uDao = new UserDao();
        User u = uDao.selectUserByUsername("aaronschroeder");
        System.out.println(AuthenticationService.authenticateUser("Muskrat", "muskt4she"));

    }
}
