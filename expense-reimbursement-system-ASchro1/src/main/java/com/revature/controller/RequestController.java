package com.revature.controller;

import com.revature.models.Request;
import com.revature.models.User;
import com.revature.service.RequestService;
import com.revature.service.UserService;
import io.javalin.http.Context;
import org.eclipse.jetty.http.HttpStatus;

import java.util.*;

public class RequestController {
    public static void postRequest(Context ctx){
        User u = ctx.sessionAttribute("user");
        Request jData = ctx.bodyAsClass(Request.class);
        if(RequestService.createRequest(u.getUsername(), jData.getType(), jData.getAmount())){
            ctx.status(HttpStatus.CREATED_201);
        }else{
            ctx.status(HttpStatus.BAD_REQUEST_400);
        }
    }

    public static void getAllRequests(Context ctx){
        User u = ctx.sessionAttribute("user");
        if(u.isManager()){
            ctx.json(RequestService.getAllRequests());
        }else{
            User u1 = UserService.getUser(u.getUsername());
            ctx.json(u1.getRequests());
        }
        ctx.status(HttpStatus.OK_200);
    }

    public static void getPendingRequests(Context ctx){
        User u = ctx.sessionAttribute("user");
        if(u.isManager()){
            ctx.json(RequestService.getAllPendingRequests());
        }else {
            ctx.json(RequestService.getPendingRequestsByUser(u.getUsername()));
        }
        ctx.status(HttpStatus.OK_200);
    }

    public static void getPastRequests(Context ctx){
        User u = ctx.sessionAttribute("user");
        if(u.isManager()){
            ctx.json(RequestService.getAllPastRequests());
        }else {
            ctx.json(RequestService.getPastRequestsByUser(u.getUsername()));
        }
        ctx.status(HttpStatus.OK_200);
    }

    public static void getUserRequests(Context ctx){
        User u = ctx.sessionAttribute("user");
        if(u.isManager()){
            String username = ctx.pathParam("username");
            User target = UserService.getUser(username);
            if(target != null){
                ctx.json(target.getRequests());
                ctx.status(HttpStatus.OK_200);
            }else{
                ctx.status(HttpStatus.NOT_FOUND_404);
            }
        }else{
            ctx.status(HttpStatus.FORBIDDEN_403);
        }
    }

    public static void getUserPastRequests(Context ctx){
        User u = ctx.sessionAttribute("user");
        if(u.isManager()){
            String username = ctx.pathParam("username");
            User target = UserService.getUser(username);
            if(target != null){
                ctx.json(RequestService.getPastRequestsByUser(target.getUsername()));
                ctx.status(HttpStatus.OK_200);
            }else{
                ctx.status(HttpStatus.NOT_FOUND_404);
            }
        }else{
            ctx.status(HttpStatus.FORBIDDEN_403);
        }
    }

    public static void getUserPendingRequests(Context ctx){
        User u = ctx.sessionAttribute("user");
        if(u.isManager()){
            String username = ctx.pathParam("username");
            User target = UserService.getUser(username);
            if(target != null){
                ctx.json(RequestService.getPendingRequestsByUser(target.getUsername()));
                ctx.status(HttpStatus.OK_200);
            }else{
                ctx.status(HttpStatus.NOT_FOUND_404);
            }
        }else{
            ctx.status(HttpStatus.FORBIDDEN_403);
        }
    }

    public static void updateRequest(Context ctx){
        User u = ctx.sessionAttribute("user");
        if(u.isManager()){
            Request jData = ctx.bodyAsClass(Request.class);
            if(RequestService.updateRequest(jData.getRequestId(), jData.getStatus())){
                ctx.status(HttpStatus.OK_200);
            }else{
                ctx.status(HttpStatus.BAD_REQUEST_400);
            }
        }else{
            ctx.status(HttpStatus.FORBIDDEN_403);
        }
    }
}
