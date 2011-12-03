package com.vcl.user;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.vcl.dao.JpaDao;

@Repository
public class UserDao extends JpaDao<User, String> {
	
	private static final String FIND_USERNAMES = 
		"SELECT u.username FROM User u ";
	
	@SuppressWarnings("unchecked")
	public List<String> findUsernames(int firstResult, int maxResult) {
		return createQueryPaged(FIND_USERNAMES, firstResult, maxResult)
			.getResultList();
	}
	
	
}
