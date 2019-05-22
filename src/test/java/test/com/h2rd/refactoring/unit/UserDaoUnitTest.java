package test.com.h2rd.refactoring.unit;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;

public class UserDaoUnitTest
{

    UserDao userDaobj;

    @Test //standalone saving test with all values correct
    public void saveUserTest1()
    {
        userDaobj = UserDao.getUserDao();

        User stuser = new User();
        stuser.setName("Fake Name");
        stuser.setEmail("fake@email.com");
        stuser.setRoles(Arrays.asList("admin", "master"));

        userDaobj.saveUser(stuser);
    }

    @Test // standalone deletion test
    public void deleteUserTest1()
    {
        userDaobj = UserDao.getUserDao();

        User dtuser = new User();
        dtuser.setName("Fake Name");
        dtuser.setEmail("fake@email.com");
        dtuser.setRoles(Arrays.asList("admin", "master"));

        try
        {
            userDaobj.deleteUser(dtuser);
        }

        catch(NullPointerException e)
        {
        }
        catch(Throwable t)
        {
            t.printStackTrace();
        }

    }

    @Test //standalone saving test with null values for admin
    public void saveUserTest2()
    {
        userDaobj = UserDao.getUserDao();

        User stuser = new User();
        stuser.setName("Fake Name");
        stuser.setEmail("fake@email.com");
        stuser.setRoles(null);

        userDaobj.saveUser(stuser);
    }

    @Test // deletion test after saving once (i.e. running saveUserTest1() )
    public void deleteUserTest2()
    {
        userDaobj = UserDao.getUserDao();

        saveUserTest1();

        User dtuser = new User();
        dtuser.setName("Fake Name");
        dtuser.setEmail("fake@email.com");
        dtuser.setRoles(Arrays.asList("admin", "master"));

        try
        {
            userDaobj.deleteUser(dtuser);
        }
        catch(NullPointerException e)
        {
        }
        catch(Throwable t)
        {
            t.printStackTrace();
        }
    }

    @Test //standalone update method test - logically not too important but just checking the update method on its own
    public void updateUserTest1()
    {
        userDaobj = UserDao.getUserDao();

        User utuser = new User();
        utuser.setName("Fake Name");
        utuser.setEmail("fake@email.com");
        utuser.setRoles(Arrays.asList("admin", "master"));

        try
        {
            userDaobj.updateUser(utuser);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test //proper update test - with email same
    public void updateUserTest2()
    {
        userDaobj = UserDao.getUserDao();

        saveUserTest1();
        User utuser = new User();
        utuser.setName("Fake Name Updated");
        utuser.setEmail("fake@email.com");
        utuser.setRoles(Arrays.asList("updated-admin", "updated-master"));

        try
        {
            userDaobj.updateUser(utuser);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test //proper update test - with email different
    public void updateUserTest3()
    {
        userDaobj = UserDao.getUserDao();

        saveUserTest1();
        User utuser = new User();
        utuser.setName("Fake Name Updated");
        utuser.setEmail("fakeupdated@email.com");
        utuser.setRoles(Arrays.asList("updated-admin", "updated-master"));

        try
        {
            userDaobj.updateUser(utuser);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test //standalone find method - with email string - primary key
    public void findUserTest1()
    {
        userDaobj = UserDao.getUserDao();

        User ftuser = new User();
        ftuser.setEmail("fake@email.com");


        try
        {
            userDaobj.findUser("fake@email.com");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test //standalone find method - with name string - primary key -  to check that email is primary key
    public void findUserTest2()
    {
        userDaobj = UserDao.getUserDao();

        User ftuser = new User();
        ftuser.setEmail("Fake Name");


        try
        {
            userDaobj.findUser("Fake Name");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}