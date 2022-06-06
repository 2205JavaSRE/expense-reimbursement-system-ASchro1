package com.revature.controller;

import com.revature.models.Request;
import com.revature.models.User;
import com.revature.service.RequestService;
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

    public static void getPendingRequests(Context ctx){
        User u = ctx.sessionAttribute("user");
        if(u.isManager()){
            ctx.json(RequestService.getAllPendingRequests());
        }else {
            List<Request> rList = u.getRequests();
            rList.removeIf(r -> !r.getStatus().equals("Pending"));
            ctx.json(rList);
        }
        ctx.status(HttpStatus.OK_200);
    }

    public static void getPastRequests(Context ctx){
        User u = ctx.sessionAttribute("user");
        if(u.isManager()){
            ctx.status(HttpStatus.BAD_REQUEST_400);
        }else {
            List<Request> rList = u.getRequests();
            rList.removeIf(r -> r.getStatus().equals("Pending"));
            
        }
    }
}
