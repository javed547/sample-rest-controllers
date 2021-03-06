/**
 * 
 */
package com.javed.controller.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.javed.controller.UserController;
import com.javed.dto.MongoUser;
import com.javed.service.MongoUserService;

/**
 * @author Mohd Javed
 *
 */
@RunWith(SpringRunner.class)
public class UserControllerTest {
	
	@InjectMocks
	private UserController testSubject = new UserController();
		
	@Mock
	private MongoUserService mongoUserService;
	
	@Before
	public void setUp() throws JsonParseException, JsonMappingException, IOException{
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test_create_user() throws JsonParseException, JsonMappingException, IOException{
		MongoUser mongoUser = getUserInstance();
		Mockito.when(mongoUserService.createMongoUser(mongoUser)).thenReturn(mongoUser);
		
		MongoUser mongoUserResult = testSubject.createMongoUser(mongoUser);
		Assert.assertEquals("Mohd Javed", mongoUserResult.getFirstName());
	}
	
	@Test
	public void test_update_user() throws JsonParseException, JsonMappingException, IOException{
		MongoUser mongoUser = getUserInstance();
		Mockito.when(mongoUserService.updateMongoUser(mongoUser)).thenReturn(mongoUser);
		
		MongoUser mongoUserResult = testSubject.updateMongoUser(mongoUser);
		Assert.assertEquals("Khan", mongoUserResult.getLastName());
	}
	
	@Test
	public void test_delete_user() throws JsonParseException, JsonMappingException, IOException{
		testSubject.readMongoUser("1");
	}
	
	@Test
	public void test_list_user() throws JsonParseException, JsonMappingException, IOException{
		Mockito.when(mongoUserService.findAll()).thenReturn(getUserInstanceList());
		
		List<MongoUser> mongoUsers = testSubject.findAll();
		Assert.assertEquals(4, mongoUsers.size());
	}

	private List<MongoUser> getUserInstanceList() {
		List<MongoUser> mongoUsers = new ArrayList<MongoUser>();
		
		MongoUser mongoUser = new MongoUser(1, "Mohd Javed", "Khan", "jal90javed@gmail.com");
		MongoUser mongoUser2 = new MongoUser(2, "Berd", "Leno", "berdLeno@gmail.com");
		MongoUser mongoUser3 = new MongoUser(3, "Laurient", "Koscielny", "laurient@gmail.com");
		MongoUser mongoUser4 = new MongoUser(4, "Socratis", "Papasthapoulus", "sokratis@gmail.com");
		
		mongoUsers.add(mongoUser);
		mongoUsers.add(mongoUser2);
		mongoUsers.add(mongoUser3);
		mongoUsers.add(mongoUser4);
		
		return mongoUsers;
	}

	private MongoUser getUserInstance() {
		MongoUser mongoUser = new MongoUser(1, "Mohd Javed", "Khan", "jal90javed@gmail.com");
		return mongoUser;
	}
}
