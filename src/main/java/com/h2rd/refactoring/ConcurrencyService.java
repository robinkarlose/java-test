package com.h2rd.refactoring;
import com.h2rd.refactoring.web.UserResource;

import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Async;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.ArrayList;
import java.util.List;

@ComponentScan
public class ConcurrencyService
{
    @Autowired
    public UserDao userDaobj;

    @Bean(name="conServ")
    public ConcurrencyService conserv()
        {
            return new ConcurrencyService();
        }

    @Async("asyncExecutor")
    public CompletableFuture<Response> addUserCR(@QueryParam("name") String name,
                                                 @QueryParam("email") String email,
                                                 @QueryParam("role") List<String> roles) throws InterruptedException
    {

        User auser = new User();
        auser.setName(name);
        auser.setEmail(email);
        auser.setRoles(roles);

        UserResource ur=new UserResource();
        ur.checkDaObjnull();

        userDaobj.saveUser(auser);
        Response res=Response.ok().entity(auser).build();

        return CompletableFuture.completedFuture(res);
    }

    @Async("asyncExecutor")
    public CompletableFuture<Response> updateUserCR(@QueryParam("name") String name,
                                                 @QueryParam("email") String email,
                                                 @QueryParam("role") List<String> roles) throws InterruptedException
    {

        User uuser = new User();
        uuser.setName(name);
        uuser.setEmail(email);
        uuser.setRoles(roles);

        UserResource ur=new UserResource();
        ur.checkDaObjnull();

        userDaobj.saveUser(uuser);
        Response res=Response.ok().entity(uuser).build();

        return CompletableFuture.completedFuture(res);
    }

    @Async("asyncExecutor")
    public CompletableFuture<Response> deleteUserCR(@QueryParam("name") String name,
                                                    @QueryParam("email") String email,
                                                    @QueryParam("role") List<String> roles) throws InterruptedException
    {

        User duser = new User();
        duser.setName(name);
        duser.setEmail(email);
        duser.setRoles(roles);

        UserResource ur=new UserResource();
        ur.checkDaObjnull();

        userDaobj.saveUser(duser);
        Response res=Response.ok().entity(duser).build();

        return CompletableFuture.completedFuture(res);
    }

    @Async("asyncExecutor")
    public CompletableFuture<Response> getUserCR() throws InterruptedException
    {
    ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {
            "classpath:/application-config.xml"
    });
    userDaobj = context.getBean(UserDao.class);
    List<User> users = userDaobj.getUsers();
        if (users == null)
    {
        users = new ArrayList<User>();
    }

    GenericEntity<List<User>> usersEntity = new GenericEntity<List<User>>(users) {};
        Response res = Response.status(200).entity(usersEntity).build();

        return CompletableFuture.completedFuture(res);
    }

    @Async("asyncExecutor")
    public CompletableFuture<Response> findUserCR(@QueryParam("email") String email)
    {
        UserResource ur=new UserResource();
        ur.checkDaObjnull();

        User fuser = userDaobj.findUser(email);
        Response res = Response.ok().entity(fuser).build();
        return CompletableFuture.completedFuture(res);
    }
}
