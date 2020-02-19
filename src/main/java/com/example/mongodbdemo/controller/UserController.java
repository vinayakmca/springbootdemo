package com.example.mongodbdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.mongodbdemo.DTO.UserDTO;
import com.example.mongodbdemo.entity.User;
import com.example.mongodbdemo.service.UserService;

@CrossOrigin(allowedHeaders = "*",allowCredentials ="false")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("")
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDto){
		
		return new ResponseEntity<UserDTO>(userService.addUser(userDto),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") String id){
		
		return new ResponseEntity<User>(userService.getUser(id),HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public ResponseEntity<Page<User>> getAllUser(@RequestParam("pageNo") int pageNo){
		
		return new ResponseEntity<Page<User>>(userService.getAllUser(pageNo),HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@RequestBody UserDTO userDto ,@PathVariable("id") String id){
		
		return new ResponseEntity<User>(userService.updateUser(userDto, id),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delteUser(@PathVariable("id") String id){
		
		return new ResponseEntity<String>(userService.deleteUser(id),HttpStatus.OK);
	}
	
	
}
