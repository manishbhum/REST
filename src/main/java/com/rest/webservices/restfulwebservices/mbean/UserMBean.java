package com.rest.webservices.restfulwebservices.mbean;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.webservices.restfulwebservices.DAO.UserDAOService;
import com.rest.webservices.restfulwebservices.entity.User;
import com.rest.webservices.restfulwebservices.exception.UserNotFoundException;

@RestController
public class UserMBean {
	
	@Autowired
	private UserDAOService userDAOService;

	@GetMapping(path = "users")
	public List<User> getAllUsers() {
		return getUserDAOService().findAll();
	}
	
	@GetMapping (path = "users/{id}")
	public User findOneUser(@PathVariable int id) {
		User user = getUserDAOService().findOne(id);
		if(user == null) {
			throw new UserNotFoundException("User not found for id = "+id);
		}
		
		return user;
	}
	
	@PostMapping (path = "users")
	public ResponseEntity createUser(@Valid @RequestBody User user) {
		User savedUser= getUserDAOService().save(user);
		
		// Return URI with ID 
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping (path = "users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = getUserDAOService().removeUser(id);
		if(user == null) {
			throw new UserNotFoundException("User not found for id = "+id);
		}
	}

	public UserDAOService getUserDAOService() {
		return userDAOService;
	}

	public void setUserDAOService(UserDAOService userDAOService) {
		this.userDAOService = userDAOService;
	}
	
	
}
