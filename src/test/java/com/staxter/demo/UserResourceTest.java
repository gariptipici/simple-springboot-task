package com.staxter.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.time.Instant;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.staxter.demo.dto.user.UserDto;
import com.staxter.demo.exception.UserAlreadyExistsException;
import com.staxter.demo.exception.UserDoesNotExistException;
import com.staxter.demo.exception.WrongPasswordException;
import com.staxter.demo.resource.user.UserResource;
import com.staxter.demo.service.user.UserService;

import static com.staxter.demo.util.SecurityUtil.asJsonString;

@RunWith(SpringRunner.class)
@WebMvcTest(UserResource.class)
public class UserResourceTest {

	private UserDto userDto;

	private UserDto userExpectedDto;

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserService service;

	@Before
	public void setup() {
		Timestamp id = Timestamp.from(Instant.now());
		userDto = new UserDto();
		userDto.setFirstName("first");
		userDto.setLastName("last");
		userDto.setUserName("username");
		userDto.setPassword("password");
	

		userExpectedDto = new UserDto();
		userExpectedDto.setId(id);
		userExpectedDto.setFirstName("first");
		userExpectedDto.setLastName("last");
		userExpectedDto.setUserName("username");

		Mockito.when(service.createUser(userDto)).thenReturn(userExpectedDto);
		Mockito.when(service.getUser("username", "password")).thenReturn(userExpectedDto);
		Mockito.when(service.getUser("wrongusername", "password")).thenThrow(new UserDoesNotExistException("USER_DOES_NOT_EXIST", "Given username does not exist"));
		Mockito.when(service.getUser("username", "wrongpassword")).thenThrow(new WrongPasswordException("WRONG_PASSWORD", "Given password does not match"));
	}

	@Test
	public void createUserTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/userservice/register")
				.content(asJsonString(userDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	
	@Test
	public void getUserTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/userservice/login")
				.param("userName", "username")
	            .param("password", "password")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted());
	}
	
	@Test
	public void getUserTestWrongUserName() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/userservice/login")
				.param("userName", "wrongusername")
	            .param("password", "password")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void getUserTestWrongPassword() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/userservice/login")
				.param("userName", "username")
	            .param("password", "wrongpassword")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isForbidden());
	}

}
