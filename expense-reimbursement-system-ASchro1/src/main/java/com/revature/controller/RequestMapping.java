package com.revature.controller;

import com.revature.util.Monitor;
import io.javalin.Javalin;
import org.eclipse.jetty.http.HttpStatus;

public class RequestMapping {
    public static void configureRoutes(Javalin app, Monitor monitor){
        app.post("/login", ctx -> {
            monitor.incrementCounter();
            AuthenticationController.authenticate(ctx);
        });

        app.post("/logout", ctx -> {
            ctx.consumeSessionAttribute("user");
            ctx.status(HttpStatus.OK_200);
        });

        app.post("request/create", ctx -> {
            if(AuthenticationController.verifyUser(ctx)){
                RequestController.postRequest(ctx);
            }else{
                ctx.status(HttpStatus.UNAUTHORIZED_401);
            }
        });

        app.get("/requests", ctx -> {
            if(AuthenticationController.verifyUser(ctx)){
                RequestController.getAllRequests(ctx);
            }else{
                ctx.status(HttpStatus.UNAUTHORIZED_401);
            }
        });

        app.get("/requests/pending", ctx -> {
            if(AuthenticationController.verifyUser(ctx)){
                RequestController.getPendingRequests(ctx);
            }else{
                ctx.status(HttpStatus.UNAUTHORIZED_401);
            }
        });

        app.get("/requests/history", ctx -> {
            if(AuthenticationController.verifyUser(ctx)){
                RequestController.getPastRequests(ctx);
            }
            else{
                ctx.status(HttpStatus.UNAUTHORIZED_401);
            }
        });

        app.get("/requests/u/{username}", ctx -> {
           if(AuthenticationController.verifyUser(ctx)){
               RequestController.getUserRequests(ctx);
           }else{
               ctx.status(HttpStatus.UNAUTHORIZED_401);
           }
        });

        app.get("requests/u/{username}/pending", ctx -> {
            if(AuthenticationController.verifyUser(ctx)){
                RequestController.getUserPendingRequests(ctx);
            }else{
                ctx.status(HttpStatus.UNAUTHORIZED_401);
            }
        });

        app.get("requests/u/{username}/history", ctx -> {
            if(AuthenticationController.verifyUser(ctx)){
                RequestController.getUserPastRequests(ctx);
            }else{
                ctx.status(HttpStatus.UNAUTHORIZED_401);
            }
        });

        app.post("request/update", ctx -> {
            if(AuthenticationController.verifyUser(ctx)){
                RequestController.updateRequest(ctx);
            }else{
                ctx.status(HttpStatus.UNAUTHORIZED_401);
            }
        });

        app.get("/metrics", ctx -> {
            ctx.result(monitor.getRegistry().scrape());
        });

        app.get("/coffee", ctx -> {
           ctx.status(HttpStatus.IM_A_TEAPOT_418);
        });

    }
}
