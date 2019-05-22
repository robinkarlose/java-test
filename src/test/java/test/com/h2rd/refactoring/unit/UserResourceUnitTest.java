package test.com.h2rd.refactoring.unit;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;
import com.h2rd.refactoring.web.UserResource;
import junit.framework.Assert;
import org.junit.Test;

import javax.ws.rs.core.Response;
import java.util.Arrays;

public class UserResourceUnitTest
{

    UserResource userResource;
    UserDao userDaobj;

    @Test // test of getUsers Method
    public void getUsersTest1()
    {

        userResource = new UserResource();
        userDaobj = UserDao.getUserDao();

        User gtuser = new User();
        gtuser.setName("fake user");
        gtuser.setEmail("fake@user.com");
        gtuser.setRoles(Arrays.asList("admin", "master"));
        userDaobj.saveUser(gtuser);

        Response response = userResource.getUsers();
        Assert.assertEquals(200, response.getStatus());
    }

    @Test //test of addUsers Method
    public void addUsersTest1()
    {

        userResource = new UserResource();
        userDaobj = UserDao.getUserDao();

        User atuser = new User();
        atuser.setName("Robin Karlose");
        atuser.setEmail("robin.karlose91@gmail.com");
        atuser.setRoles(Arrays.asList("admin", "master"));
        userDaobj.saveUser(atuser);

        Response response = userResource.addUser(atuser.getName(),atuser.getEmail(),atuser.getRoles());
        Assert.assertEquals(200, response.getStatus());

    }

    @Test //test of deleteUsers Method
    public void deleteUsersTest1()
    {

        userResource = new UserResource();
        userDaobj = UserDao.getUserDao();

        addUsersTest1();

        User dtuser = new User();
        dtuser.setName("Robin Karlose");
        dtuser.setEmail("robin.karlose91@gmail.com");
        dtuser.setRoles(Arrays.asList("admin", "master"));

        Response response = userResource.deleteUser(dtuser.getName(), dtuser.getEmail(), dtuser.getRoles());
        Assert.assertEquals(200, response.getStatus());

    }

    @Test //test of updateUsers Method
    public void updateUsersTest1()
    {

        userResource = new UserResource();
        userDaobj = UserDao.getUserDao();

        addUsersTest1();

        User utuser = new User();
        utuser.setName("Robin Oliver Karlose");
        utuser.setEmail("robin.karlose91@gmail.com");
        utuser.setRoles(Arrays.asList("super-admin", "super-master"));
        userDaobj.saveUser(utuser);

        Response response = userResource.updateUser(utuser.getName(),utuser.getEmail(),utuser.getRoles());
        Assert.assertEquals(200, response.getStatus());

    }

    @Test //test of findUsers Method
    public void findUsersTest1()
    {

        userResource = new UserResource();
        userDaobj = UserDao.getUserDao();

        addUsersTest1();

        User ftuser = new User();
        ftuser.setName("Robin Oliver Karlose");
        ftuser.setEmail("robin.karlose91@gmail.com");
        ftuser.setRoles(Arrays.asList("super-admin", "super-master"));
        userDaobj.saveUser(ftuser);

        Response response = userResource.findUser(ftuser.getEmail());
        Assert.assertEquals(200, response.getStatus());

    }

    //Concurrent Requests Methods for UserResource Standalone testing - detailed testing in IntegrationTesting
    @Test // test of getUsersCR Method
    public void getUsersCRTest1()
    {

        userResource = new UserResource();
        userDaobj = UserDao.getUserDao();

        User gtuser = new User();
        gtuser.setName("fake user");
        gtuser.setEmail("fake@user.com");
        gtuser.setRoles(Arrays.asList("admin", "master"));
        userDaobj.saveUser(gtuser);

        Response response = userResource.getUsersCR();
        Assert.assertEquals(200, response.getStatus());
    }

    @Test //test of addUsersCR Method
    public void addUsersCRTest1()
    {

        userResource = new UserResource();
        userDaobj = UserDao.getUserDao();

        User atuser = new User();
        atuser.setName("Robin Karlose");
        atuser.setEmail("robin.karlose91@gmail.com");
        atuser.setRoles(Arrays.asList("admin", "master"));
        userDaobj.saveUser(atuser);

        Response response = userResource.addUserCR(atuser.getName(),atuser.getEmail(),atuser.getRoles());
        Assert.assertEquals(200, response.getStatus());

    }

    @Test //test of deleteUsersCR Method
    public void deleteUsersCRTest1()
    {

        userResource = new UserResource();
        userDaobj = UserDao.getUserDao();

        addUsersCRTest1();

        User dtuser = new User();
        dtuser.setName("Robin Karlose");
        dtuser.setEmail("robin.karlose91@gmail.com");
        dtuser.setRoles(Arrays.asList("admin", "master"));


        Response response = userResource.deleteUserCR(dtuser.getName(),dtuser.getEmail(),dtuser.getRoles());
            Assert.assertEquals(200, response.getStatus());



    }

    @Test //test of updateUsersCR Method
    public void updateUsersCRTest1()
    {

        userResource = new UserResource();
        userDaobj = UserDao.getUserDao();

        addUsersCRTest1();

        User utuser = new User();
        utuser.setName("Robin Oliver Karlose");
        utuser.setEmail("robin.karlose91@gmail.com");
        utuser.setRoles(Arrays.asList("super-admin", "super-master"));
        userDaobj.saveUser(utuser);

        Response response = userResource.updateUserCR(utuser.getName(),utuser.getEmail(),utuser.getRoles());
        Assert.assertEquals(200, response.getStatus());

    }

    @Test //test of findUsersCR Method
    public void findUsersCRTest1()
    {

        userResource = new UserResource();
        userDaobj = UserDao.getUserDao();

        addUsersCRTest1();

        User ftuser = new User();
        ftuser.setName("Robin Oliver Karlose");
        ftuser.setEmail("robin.karlose91@gmail.com");
        ftuser.setRoles(Arrays.asList("super-admin", "super-master"));
        userDaobj.saveUser(ftuser);

        Response response = userResource.findUserCR(ftuser.getEmail());
        Assert.assertEquals(200, response.getStatus());

    }

}
