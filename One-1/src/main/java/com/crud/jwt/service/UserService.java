package com.crud.jwt.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(username.equals("pavithra")) {
			return new User("pavithra","password",new ArrayList<>());
		}
		else if(username.equals("pavi")) {
			return new User("pavi","pwd",new ArrayList<>());

		}
		else {
			throw new UsernameNotFoundException("User not found"+username);
		}	}
	

}
