package com.sprint1.tourmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sprint1.tourmanagement.entity.User;
import com.sprint1.tourmanagement.exception.UserNotFoundException;
import com.sprint1.tourmanagement.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	//for User Login
	@Override
	public String userLogin(String userType, String userId, String password) {
		
		//fisrt validate userId whether it is there in the user entity or not. If not throw exception
		User validateUser = userRepository.findByuserUserId(userId);
		if (validateUser == null) {
			throw new UserNotFoundException("There is no user with Id: " + userId);
		}

		//Decrypt(Shift 3) the password first and then validate it
		String pwd = validateUser.getUserPassword();
		char c;
		for (int i = 0; i < pwd.length(); i++) {
			c = (char) (pwd.charAt(i) - 3);
			pwd = pwd.substring(0, i) + c + pwd.substring(i + 1);
		}
		if (!pwd.equals(password)) {
			throw new UserNotFoundException("Invalid Password");
		}

		//check whether there is user with the given user type
		String utype = validateUser.getUserType();
		if (!utype.equalsIgnoreCase(userType)) {
			throw new UserNotFoundException("Invalid User");
		}
		
		//Succeded case
		return "User Successfully Logged in";
	}

	@Override
	public String saveUser(User user) {
		
		//First check user type is in Admin,Staff,Customer
		String userType = user.getUserType().toLowerCase();
		if (!userType.equals("admin") || !userType.equals("staff") || !userType.equals("customer")) {
			return "User Type should be Admin or Staff or Customer";
		}

		//Check whether the given userId is existed or not
		String useruserid = user.getUserUserId();
		User user1 = userRepository.findByuserUserId(useruserid);
		if (user1 != null) {
			return "User Already Existed with Id: " + user.getUserUserId();
		}

		//Encrypting(Shift 3) the password before storing into database
		String password = user.getUserPassword();
		char c;
		for (int i = 0; i < password.length(); i++) {
			c = (char) (password.charAt(i) + 3);
			password = password.substring(0, i) + c + password.substring(i + 1);
		}
		user.setUserPassword(password);
		userRepository.save(user);
		return "User added Successfully";
	}

	
	@Override
	public User updateUser(User user) {
		//First find whether the user is existed to update if not throw exception
		Optional<User> user1 = userRepository.findById(user.getUserId());
		if (user1.isEmpty()) {
			throw new UserNotFoundException("There is no user existed with id: " + user.getUserId());
		}
		User user2 = new User();
		return userRepository.save(user2);
	}

	//Admin Login using userId and Password with out entering user type 
	@Override
	public void validLogin(String userId, String password) {
		User user = userRepository.findByuserUserId(userId);
		if (user == null) {
			throw new UserNotFoundException("There is no user with Id: " + userId);
		}
		String userType = user.getUserType();
		if (!userType.equalsIgnoreCase("Admin")) {
			throw new UserNotFoundException("Users other than admin can't view the Reservations");
		}
		
		//validate the password after decryption
		String pwd = user.getUserPassword();
		char c;
		for (int i = 0; i < pwd.length(); i++) {
			c = (char) (pwd.charAt(i) - 3);
			pwd = pwd.substring(0, i) + c + pwd.substring(i + 1);
		}
		if (!pwd.equals(password)) {
			throw new UserNotFoundException("Invalid Password");
		}

		String utype = user.getUserType();
		if (!utype.equalsIgnoreCase(userType)) {
			throw new UserNotFoundException("Invalid User");
		}
	}

	//Delete User by giving userId
	@Override
	public String deleteUserById(int userId) {
		//first find the user
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isEmpty()) {
			return "There is No User with Id:" + userId;
		}
		//delete user
		userRepository.deleteById(userId);
		return "User with Id: " + userId + " Delete Successfully";
	}

	//Finding all the users
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	//Finding user by giving the Id
	@Override
	public User findUserById(int userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isEmpty()) {
			throw new UserNotFoundException("There is no User with Id:" + userId);
		}
		User user = optionalUser.get();
		return user;
	}
}