package com.example.mongodbdemo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.example.mongodbdemo.config.JwtToken;
import com.example.mongodbdemo.serviceimpl.JwtUserDetailsService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.authentication.AuthenticationManager;

@RestController
@CrossOrigin
public class AuthController {
	
	   @Autowired
	    private AuthenticationManager authenticationManager;

	    @Autowired
	    private JwtToken jwtToken;

	    @Autowired
	    private JwtUserDetailsService jwtUserDetailsService;
	    
	    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	    public ResponseEntity<?> createAuthenticationToken(@RequestBody Map<String,String> authenticationRequest) throws Exception {


	        authenticate(authenticationRequest.get("userName"), authenticationRequest.get("password"));

	        final UserDetails userDetails = jwtUserDetailsService

	                .loadUserByUsername(authenticationRequest.get("userName"));

	        final String token = jwtToken.generateToken(userDetails);

	        return ResponseEntity.ok(token);

	    }

	    private void authenticate(String username, String password) throws Exception {

	        try {

	            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

	        } catch (DisabledException e) {

	            throw new Exception("USER_DISABLED", e);

	        } catch (BadCredentialsException e) {

	            throw new Exception("INVALID_CREDENTIALS", e);

	        }

	    }

}
