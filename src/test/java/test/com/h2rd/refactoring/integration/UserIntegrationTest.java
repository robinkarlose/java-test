package test.com.h2rd.refactoring.integration;

import java.util.ArrayList;
import java.util.Arrays;

import javax.ws.rs.core.Response;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import com.h2rd.refactoring.usermanagement.User;
import com.h2rd.refactoring.usermanagement.UserDao;
import com.h2rd.refactoring.web.UserResource;

public class UserIntegrationTest
{
	@Ignore("null values for role parameter")
	@Test
	public void createUserTest()
    {
		UserResource userResource = new UserResource();
		
		User integration = new User();
        integration.setName("integrationInitial");
        integration.setEmail("initial@integration.com");
        integration.setRoles(new ArrayList<String>());
        
        Response response = userResource.addUser(integration.getName(), integration.getEmail(), integration.getRoles());
        Assert.assertEquals(200, response.getStatus());
	}

    @Ignore("null values for role parameter")
	@Test
	public void updateUserTest()
    {
		UserResource userResource = new UserResource();
		
		createUserTest();
        
        User updated = new User();
        updated.setName("integrationUpdated");
        updated.setEmail("initial@integration.com");
        updated.setRoles(new ArrayList<String>());
        
        Response response = userResource.updateUser(updated.getName(), updated.getEmail(), updated.getRoles());
        Assert.assertEquals(200, response.getStatus());
	}


    @Test
    public void createUserTest2()
    {
        UserResource userResource = new UserResource();

        User integration = new User();
        integration.setName("integrationInitial");
        integration.setEmail("initial@integration.com");
        integration.setRoles(Arrays.asList("admin", "master"));

        Response response = userResource.addUser(integration.getName(), integration.getEmail(), integration.getRoles());
        Assert.assertEquals(200, response.getStatus());
    }

    @Test
    public void updateUserTest2()
    {
        UserResource userResource = new UserResource();

        createUserTest2();

        User updated = new User();
        updated.setName("integrationUpdated");
        updated.setEmail("initial@integration.com");
        updated.setRoles(Arrays.asList("super-admin", "super-master"));

        Response response = userResource.updateUser(updated.getName(), updated.getEmail(), updated.getRoles());
        Assert.assertEquals(200, response.getStatus());
    }

    @Test //create 2 users
    public void createIntegrationTest1()
    {
        UserResource userResource = new UserResource();

        User u1 = new User();
        u1.setName("Robin Karlose");
        u1.setEmail("robin.karlose91@gmail.com");
        u1.setRoles(Arrays.asList("admin", "master"));

        Response response1 = userResource.addUser(u1.getName(), u1.getEmail(), u1.getRoles());

        User u2 = new User();
        u2.setName("Rod Johnson");
        u2.setEmail("rod.johnson@gmail.com");
        u2.setRoles(Arrays.asList("creator-admin", "creator-master"));

        Response response2 = userResource.addUser(u2.getName(), u2.getEmail(), u2.getRoles());

        Assert.assertEquals(200, response1.getStatus());
        Assert.assertEquals(200, response2.getStatus());
    }


    @Test //create . update and delete a user
    public void createUpdateDeleteUserTest1()
    {
        UserResource userResource = new UserResource();

        createUserTest2();
        updateUserTest2();

        User u3 = new User();
        u3.setName("integrationUpdated");
        u3.setEmail("initial@integration.com");
        u3.setRoles(Arrays.asList("super-admin", "super-master"));

        Response response = userResource.deleteUser(u3.getName(), u3.getEmail(), u3.getRoles());
        Assert.assertEquals(200, response.getStatus());
    }

    @Test // create and find a user
    public void createFindUserTest1()
    {
        UserResource userResource = new UserResource();
        createUserTest2();

        User u4=new User();
        u4.setEmail("initial@integration.com");

        Response response = userResource.findUser(u4.getEmail());
        Assert.assertEquals(200, response.getStatus());
	}


}
