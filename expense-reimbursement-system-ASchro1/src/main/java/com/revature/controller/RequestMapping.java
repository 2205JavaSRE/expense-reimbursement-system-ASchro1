package com.revature.controller;

import io.javalin.Javalin;
import org.eclipse.jetty.http.HttpStatus;

public class RequestMapping {
    public static void configureRoutes(Javalin app){
        app.post("/login", ctx -> {
            AuthenticationController.authenticate(ctx);
        });

        app.post("/logout", ctx -> {
            ctx.consumeSessionAttribute("user");
            ctx.status(HttpStatus.ACCEPTED_202);
        });

        app.post("request/create", ctx -> {
            if(AuthenticationController.verifyUser(ctx)){
                RequestController.postRequest(ctx);
            }else{
                ctx.status(HttpStatus.FORBIDDEN_403);
            }
        });

        app.get("/requests", ctx -> {
            if(AuthenticationController.verifyUser(ctx)){
                RequestController.getAllRequests(ctx);
            }else{
                ctx.status(HttpStatus.FORBIDDEN_403);
            }
        });

        app.get("/requests/pending", ctx -> {
            if(AuthenticationController.verifyUser(ctx)){
                RequestController.getPendingRequests(ctx);
            }else{
                ctx.status(HttpStatus.FORBIDDEN_403);
            }
        });

        app.get("/requests/history", ctx -> {
            if(AuthenticationController.verifyUser(ctx)){
                RequestController.getPastRequests(ctx);
            }
            else{
                ctx.status(HttpStatus.FORBIDDEN_403);
            }
        });

        app.get("/requests/u/{username}", ctx -> {
           if(AuthenticationController.verifyUser(ctx)){
               RequestController.getUserRequests(ctx);
           }else{
               ctx.status(HttpStatus.FORBIDDEN_403);
           }
        });

        app.get("requests/u/{username}/pending", ctx -> {
            if(AuthenticationController.verifyUser(ctx)){
                RequestController.getUserPendingRequests(ctx);
            }else{
                ctx.status(HttpStatus.FORBIDDEN_403);
            }
        });

        app.get("requests/u/{username}/history", ctx -> {
            if(AuthenticationController.verifyUser(ctx)){
                RequestController.getUserPastRequests(ctx);
            }else{
                ctx.status(HttpStatus.FORBIDDEN_403);
            }
        });

        app.post("request/update", ctx -> {
            if(AuthenticationController.verifyUser(ctx)){
                RequestController.updateRequest(ctx);
            }else{
                ctx.status(HttpStatus.FORBIDDEN_403);
            }
        });
    }
}
