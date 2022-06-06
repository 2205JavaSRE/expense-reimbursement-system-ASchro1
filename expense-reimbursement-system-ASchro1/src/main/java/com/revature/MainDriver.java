package com.revature;
import com.revature.controller.RequestMapping;
import com.revature.dao.UserDao;
import com.revature.models.User;
import com.revature.service.AuthenticationService;
import com.revature.service.RequestService;
import com.revature.service.UserService;
import io.javalin.Javalin;
public class MainDriver {
    public static void main(String[] args){
        Javalin myApp = Javalin.create().start(8501);
        RequestMapping.configureRoutes(myApp);
    }
}
