package com.example.mongodbdemo.serviceimpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.mongodbdemo.entity.User;
import com.example.mongodbdemo.repository.UserRepository;

@Component
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		 User user=userRepository.findByFirstName(username);
		 if(user==null) {
			 throw new UsernameNotFoundException("User not found with username: " + username);
		 }
		 return new org.springframework.security.core.userdetails.User(user.getFirstName(), user.getLastName(),
	                new ArrayList<>());
    }
}
