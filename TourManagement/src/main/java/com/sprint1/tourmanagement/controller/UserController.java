package com.sprint1.tourmanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.tourmanagement.entity.User;
import com.sprint1.tourmanagement.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("CreateNewUser")
	public ResponseEntity<String> addUser(@Valid @RequestBody User user) {
		String user1 = userService.saveUser(user);
		ResponseEntity<String> responseEntity = new ResponseEntity<>(user1, HttpStatus.CREATED);
		return responseEntity;
	}

	@GetMapping("user/login/{userType}/{userId}/{password}")
	public ResponseEntity<String> doLogin(@PathVariable("userType") String userType,
			@PathVariable("userId") String userId, @PathVariable("password") String password) {
		String user = userService.userLogin(userType, userId, password);
		ResponseEntity<String> responseEntity = new ResponseEntity<>(user, HttpStatus.OK);
		return responseEntity;
	}

	@PutMapping("UpdateUserDetails")
	public ResponseEntity<User> modifyUser(@RequestBody User user) {
		User user1 = userService.updateUser(user);
		ResponseEntity<User> responseEntity = new ResponseEntity<>(user1, HttpStatus.OK);
		return responseEntity;
	}

	@DeleteMapping("DeleteUser/{userId}")
	public ResponseEntity<String> deleteUser(@PathVariable("userId") int userId) {
		String response = userService.deleteUserById(userId);
		ResponseEntity<String> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
		return responseEntity;

	}

	@GetMapping("DisplayAllUsers")
	public ResponseEntity<List<User>> fetchAllUsers() {
		List<User> usersList = userService.getAllUsers();
		ResponseEntity<List<User>> responseEntity = new ResponseEntity<>(usersList, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("FindUserById/{userId}")
	public ResponseEntity<User> findUserById(@PathVariable("userId") int userId) {
		User user = userService.findUserById(userId);
		ResponseEntity<User> responseEntity = new ResponseEntity<>(user, HttpStatus.OK);
		return responseEntity;
	}
}
