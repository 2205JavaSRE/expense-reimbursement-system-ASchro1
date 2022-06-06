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

        app.get("/requests/pending", ctx -> {
            if(AuthenticationController.verifyUser(ctx)){
                RequestController.getPendingRequests(ctx);
            }else{
                ctx.status(HttpStatus.FORBIDDEN_403);
            }
        });

        app.get("/requests/history", ctx -> {
            if(AuthenticationController.verifyUser(ctx)){

            }
        });
    }
}
