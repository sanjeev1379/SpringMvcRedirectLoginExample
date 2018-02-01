package com.nareshit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nareshit.dao.UserDAO;
import com.nareshit.domain.Login;
@Service
public class UserServiceImpl 
implements UserService{
	@Autowired
private UserDAO userDAO;
	public Login login(Login login) {
	 login= userDAO.login(login);
		return login;
	}
	
}
