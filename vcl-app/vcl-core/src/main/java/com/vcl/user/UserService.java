package com.vcl.user;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
	
	@Inject
	private UserDao dao;
	
	public User persist(User u) {
		return dao.persist(u);
	}
	
	public User merge(User u) {
		return dao.merge(u);
	}
	
	public List<String> findUsernames(int firstResult, int maxResult){
		return dao.findUsernames(firstResult, maxResult);
	}
	
	public void remove(User u) {
		dao.removeById(u.getUsername());
	}
	
	public User findByUsername(String username) {
		return dao.findById(username);
	}
	
	public List<User> findAllPaged(int firstResult, int maxResult) {
		return dao.findAllPaged(firstResult, maxResult);
	}
	
}
