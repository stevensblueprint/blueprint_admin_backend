package com.sitblueprint.admin.controller.users;

import com.sitblueprint.admin.model.users.User;
import com.sitblueprint.admin.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("all")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> getUser(@PathVariable("userId") Long userId) {
		try {
			User user = userService.getUserById(userId);
			return ResponseEntity.ok(user);
		} catch (NumberFormatException e) {
			return ResponseEntity.badRequest().body("Invalid user id format");
		}
	}

	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@PutMapping
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}

	@DeleteMapping
	public void deleteUser(String userId) {
		userService.deleteUserById(Long.parseLong(userId));
	}

	@PostMapping("enable/{userId}")
	public void enableUser(@PathVariable("userId") String userId) {
		userService.enableUserById(Long.parseLong(userId));
	}

	@PostMapping("disable/{userId}")
	public void disableUser(@PathVariable("userId") String userId) {
		userService.disableUserById(Long.parseLong(userId));
	}

	@PutMapping("reset_password")
	public void resetPassword(@RequestBody String userId, @RequestBody String newPassword) {
		userService.resetPassword(Long.parseLong(userId), newPassword);
	}
}
