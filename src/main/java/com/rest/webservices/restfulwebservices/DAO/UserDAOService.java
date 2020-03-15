package com.rest.webservices.restfulwebservices.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.webservices.restfulwebservices.entity.User;

@Component
public class UserDAOService {
	private static List<User> users = new ArrayList<>();
	private static int userCount =3;
	
	static {
		users.add(new User(1,"Manish", new Date()));
		users.add(new User(2,"Kamal", new Date()));
		users.add(new User(3,"Avinash", new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user) {
		if(user.getId() == null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}
	
	public User removeUser(Integer id) {
		Iterator iterator =  users.iterator();
		while(iterator.hasNext()) {
			User user = (User) iterator.next();
			if(id.equals(user.getId())) {
				iterator.remove();
				return user;
			}
		}
		return null;
	}
	

	
	public User findOne(Integer id) {
		Iterator iterator =  users.iterator();
		while(iterator.hasNext()) {
			User user = (User) iterator.next();
			if(id.equals(user.getId())) {
				return user;
			}
		}
		return null;
	}

}
