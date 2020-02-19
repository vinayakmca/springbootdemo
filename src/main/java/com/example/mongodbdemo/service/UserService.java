package com.example.mongodbdemo.service;


import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.mongodbdemo.DTO.UserDTO;
import com.example.mongodbdemo.entity.User;


public interface UserService {

	  public UserDTO addUser(UserDTO userDTO);
	    public User getUser(String id);
	    public User updateUser(UserDTO userUpdateDTO, String id);
	    public String deleteUser(String id);
	    public Page<User> getAllUser(int pageNo);
	    public UserDetails findByUserName(String name);
}
