package com.sitblueprint.admin.controller.users;

import com.sitblueprint.admin.model.users.Attendance;
import com.sitblueprint.admin.model.users.User;
import com.sitblueprint.admin.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @PostMapping
    public ResponseEntity<Attendance> markAttendance(@PathVariable("userId") Long userId, @PathVariable("date") LocalDateTime date) {
        Attendance attendance = userService.markAttendance(userId, date);
        return ResponseEntity.ok(attendance);
    }

    @GetMapping("/attendance/{date}")
    public ResponseEntity<Attendance> getAttendance(@PathVariable("userId") Long userId, @RequestParam("date") LocalDateTime date) {
        Attendance attendance = userService.getAttendance(userId, date);
        return ResponseEntity.ok(attendance);
    }

    @GetMapping("/attendance")
    public ResponseEntity<List<Attendance>> getAllAttendance(@PathVariable("userId") Long userId, @RequestParam(required = false) LocalDateTime startDate, @RequestParam(required = false) LocalDateTime endDate) {
        List<Attendance> attendances = userService.getAllAttendance(userId, startDate, endDate);
        return ResponseEntity.ok(attendances);
    }

    @PutMapping("/attendance/{date}")
    public ResponseEntity<Attendance> updateAttendance(@PathVariable("userId") Long userId, @RequestParam("date") LocalDateTime date) {
        Attendance updatedAttendance = userService.updateAttendance(userId, date);

        if (updatedAttendance == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(updatedAttendance);
        }
    }

    @DeleteMapping("/attendance/{date}")
    public ResponseEntity<Attendance> deleteAttendance(@PathVariable("userId") Long userId, @RequestParam("date") LocalDateTime date, @RequestParam("status") Boolean status) {
        userService.deleteAttendance(userId, date);
        return ResponseEntity.noContent().build();
    }
}
