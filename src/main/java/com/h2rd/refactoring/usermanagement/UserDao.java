package com.h2rd.refactoring.usermanagement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;


public class UserDao
{

    public ArrayList<User> users;

    public static UserDao userDaobj;

    public static UserDao getUserDao()
    {
        if (userDaobj == null)
        {
            userDaobj = new UserDao();
        }
        return userDaobj;
    }

    public void saveUser(User suser)
    {
        try
        {
            if (users == null)
            {
                users = new ArrayList<User>();
            }
            if (suser.roles.isEmpty())
                throw new RuntimeException();
            users.add(suser);
        }
        catch (RuntimeException re)
        {
            System.out.println("User must be defined at least one role");
            System.out.println("Error - try adding again with a non null role");

        }
        /*
        catch (Throwable e)
        {
            e.printStackTrace();

        }*/
    }

    public ArrayList<User> getUsers()
    {
        try
        {
            return users;
        }
        catch (Throwable e)
        {
            System.out.println("Error in getUsers method");
            return null;
        }
    }

    public void deleteUser(User userToDelete)
    {
        try
        {
            for (User userIter : users)
            {
                if (userIter.getEmail() == userToDelete.getEmail())
                {
                    users.remove(userIter);
                }
            }
        }
        catch (ConcurrentModificationException e)
        {
            e.getMessage();
        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }

    }

    public void updateUser(User userToUpdate)
    {
        try
        {
            for (User user : users)
            {
                if (user.getEmail() == userToUpdate.getEmail())
                {
                    user.setName(userToUpdate.getName());
                    user.setRoles(userToUpdate.getRoles());
                }
            }
        }
        catch (Throwable e)
        {
            e.printStackTrace();
        }
    }

    public User findUser(String email)
    {
        try {
            for (User userIter : users)
            {
                if (userIter.getEmail() == email)
                {
                    return userIter;
                }
            }
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
