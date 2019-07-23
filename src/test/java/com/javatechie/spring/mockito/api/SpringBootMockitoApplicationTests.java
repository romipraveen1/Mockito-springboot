package com.javatechie.spring.mockito.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.javatechie.spring.mockito.api.dao.UserRepository;
import com.javatechie.spring.mockito.api.model.User;
import com.javatechie.spring.mockito.api.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootMockitoApplicationTests {

	@Autowired
	private UserService service;

	@MockBean
	private UserRepository repository;

	@Test
	public void getUsersTest() {
		
		List<User> user = new ArrayList<User>();
		User user1 =new User();
		user1.setId(376);
		user1.setAddress("USA");
		user1.setAge(31);
		user1.setName("romi");
		
		User user2 =new User();
		user2.setId(375);
		user2.setAddress("USA");
		user2.setAge(31);
		user2.setName("romi");
		
		user.add(user1);
		user.add(user2);
		when(repository.findAll()).thenReturn(user);
				
		assertEquals(2, service.getUsers().size());
	}

	@Test
	public void getUserbyAddressTest() {
		List<User> user = new ArrayList<User>();
		User user1 =new User();
		user1.setId(376);
		user1.setAddress("USA");
		user1.setAge(31);
		user1.setName("romi");
		user.add(user1);
		String address = "USA";
		when(repository.findByAddress(address))
				.thenReturn(user);
		assertEquals(1, service.getUserbyAddress(address).size());
	}

	@Test
	public void saveUserTest() {
		
		
		User user1 =new User();
		user1.setId(376);
		user1.setAddress("USA");
		user1.setAge(31);
		user1.setName("romi");
		when(repository.save(user1)).thenReturn(user1);
		assertEquals(user1, service.addUser(user1));
	}

	@Test
	public void deleteUserTest() {
		User user1 =new User();
		user1.setId(376);
		user1.setAddress("USA");
		user1.setAge(31);
		user1.setName("romi");
 
		service.deleteUser(user1);
		verify(repository, times(1)).delete(user1);
	}

}
