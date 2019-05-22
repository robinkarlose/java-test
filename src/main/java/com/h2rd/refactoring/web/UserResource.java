package com.h2rd.refactoring.web;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.ArrayList;
import java.util.List;

@Controller
@Path("/users")
@Repository
public class UserResource
{
    @Autowired
    public UserDao userDaobj;

    public void checkDaObjnull()
    {
        if (userDaobj == null)
        {
            userDaobj = UserDao.getUserDao();
        }
    }


    @POST
    @Path("add/")
    public Response addUser(@QueryParam("name") String name,
                            @QueryParam("email") String email,
                            @QueryParam("role") List<String> roles)
    {

        User auser = new User();
        auser.setName(name);
        auser.setEmail(email);
        auser.setRoles(roles);

        checkDaObjnull();

        userDaobj.saveUser(auser);
        return Response.ok().entity(auser).build();
    }


    @POST
    @Path("update/")
    public Response updateUser(@QueryParam("name") String name,
                               @QueryParam("email") String email,
                               @QueryParam("role") List<String> roles)
    {

        User uuser = new User();
        uuser.setName(name);
        uuser.setEmail(email);
        uuser.setRoles(roles);

        checkDaObjnull();

        userDaobj.updateUser(uuser);
        return Response.ok().entity(uuser).build();
    }


    @POST
    @Path("delete/")
    public Response deleteUser(@QueryParam("name") String name,
                               @QueryParam("email") String email,
                               @QueryParam("role") List<String> roles)
    {
        User duser = new User();
        duser.setName(name);
        duser.setEmail(email);
        duser.setRoles(roles);

        checkDaObjnull();

        userDaobj.deleteUser(duser);
        return Response.ok().entity(duser).build();
    }


    @GET
    @Path("find/")
    public Response getUsers()
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
        return Response.status(200).entity(usersEntity).build();
    }


    @GET
    @Path("search/")
    public Response findUser(@QueryParam("email") String email)
    {

        checkDaObjnull();

        User fuser = userDaobj.findUser(email);
        return Response.ok().entity(fuser).build();
    }



    //SUPPORT FOR CONCURRENT REQUESTS
    @RequestMapping(value = "/concurrent/add/", method = RequestMethod.POST)
    public Response addUserCR(@QueryParam("name") String name,
                              @QueryParam("email") String email,
                              @QueryParam("role") List<String> roles)
    {

        User auser = new User();
        auser.setName(name);
        auser.setEmail(email);
        auser.setRoles(roles);

        checkDaObjnull();

        userDaobj.saveUser(auser);
        return Response.ok().entity(auser).build();
    }

    @RequestMapping(value = "/concurrent/update/", method = RequestMethod.POST)
    public Response updateUserCR(@QueryParam("name") String name,
                               @QueryParam("email") String email,
                               @QueryParam("role") List<String> roles)
    {

        User uuser = new User();
        uuser.setName(name);
        uuser.setEmail(email);
        uuser.setRoles(roles);

        checkDaObjnull();

        userDaobj.updateUser(uuser);
        return Response.ok().entity(uuser).build();
    }
    @RequestMapping(value = "/concurrent/delete/", method = RequestMethod.POST)
    public Response deleteUserCR(@QueryParam("name") String name,
                               @QueryParam("email") String email,
                               @QueryParam("role") List<String> roles)
    {
        User duser = new User();
        duser.setName(name);
        duser.setEmail(email);
        duser.setRoles(roles);

        checkDaObjnull();

        userDaobj.deleteUser(duser);
        return Response.ok().entity(duser).build();
    }

    @RequestMapping(value = "/concurrent/find/", method = RequestMethod.GET)
    public Response getUsersCR()
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
        return Response.status(200).entity(usersEntity).build();
    }

    @RequestMapping(value = "/concurrent/search/", method = RequestMethod.GET)
    public Response findUserCR(@QueryParam("email") String email)
    {

        checkDaObjnull();

        User fuser = userDaobj.findUser(email);
        return Response.ok().entity(fuser).build();
    }
}
