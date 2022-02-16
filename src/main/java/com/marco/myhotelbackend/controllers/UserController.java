package com.marco.myhotelbackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marco.myhotelbackend.models.User;
import com.marco.myhotelbackend.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/save-update-user", method = RequestMethod.POST)
	private ResponseEntity<User> saveUpdateUser(
			@RequestBody User user) throws Exception {

		return new ResponseEntity<User>(userService.addOrEdit(user), HttpStatus.OK) ;

	}
	
	@RequestMapping(value = "/sign-in", method = RequestMethod.POST)
	private ResponseEntity<User> signIn(
			@RequestBody User user) throws Exception {

		return new ResponseEntity<User>(userService.login(user.getUserName(),user.getPassword()), HttpStatus.OK) ;

	}
	
}
