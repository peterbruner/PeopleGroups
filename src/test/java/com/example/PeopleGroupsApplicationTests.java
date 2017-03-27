package com.example;

import com.example.entities.User;
import com.example.services.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PeopleGroupsApplicationTests {

	@Autowired
	WebApplicationContext wap;

	@Autowired
	UserRepository users;

	MockMvc mockMvc;

	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wap).build();
	}

	@Test
	public void contextLoads() {

	}

	@Test  //got to work hard on this!!! more questions.
	public void getUsers() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String mappedUser = mapper.writeValueAsString(users.findAll());

		ResultActions ra = mockMvc.perform(
				MockMvcRequestBuilders.get("/user"))
				.andExpect(status().isOk())
				.andExpect(content().json(mappedUser));
	}

	@Test
	public void addUser() throws Exception{
		User user = new User();
		user.setAffiliation("testAffiliation");
		user.setName("testPerson");
		user.setAddress("123 Test st.");
		user.setEmailAddress("testperson@test.com");
		user.setPhone("1234567890");
		user.setFlavor("testflavor");
		// should I also put in a set userId?

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(user);

		mockMvc.perform(
				MockMvcRequestBuilders.post("/user")
						.content(json)
						.contentType("application/json")
		);

		Assert.assertTrue(users.count() == 2);

	}

	@Test
	public void updateUser() throws Exception{
		User user = new User();
		user.setAffiliation("testAffiliation");
		user.setName("eight");
		user.setAddress("123 Test st.");
		user.setEmailAddress("testperson@test.com");
		user.setPhone("1234567890");
		user.setFlavor("testflavor");
		user.setId(1);
		mockMvc.perform(
				MockMvcRequestBuilders.put("/user")
		);
		Assert.assertTrue(users.findOne(1).getName().equals("seven"));
	}


	@Test
	public void deleteUser() throws Exception{

		long countA = users.count();

		mockMvc.perform(
				MockMvcRequestBuilders.delete("/user/1")
		);

		long countB = users.count();
		Assert.assertFalse(countB == countA);

		Assert.assertTrue(countB < countA);
	}

	@Test
	public void populator() {

	}

}
