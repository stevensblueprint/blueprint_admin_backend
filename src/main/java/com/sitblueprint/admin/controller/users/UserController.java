package com.sitblueprint.admin.controller.users;

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

    @PostMapping("/{userId}/attendance")
    public ResponseEntity<?> recordAttendance(@PathVariable Long userId, @RequestParam LocalDateTime date, @RequestParam Boolean status) {
        try {
            userService.recordAttendance(userId, date, status);
            return ResponseEntity.ok("Attendance recorded successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to record attendance.");
        }
    }

    @GetMapping("/{userId}/attendance")
    public ResponseEntity<?> getAttendance(@PathVariable Long userId, @RequestParam LocalDateTime date) {
        try {
            Boolean attendanceStatus = userService.getAttendance(userId, date);
            return ResponseEntity.ok(attendanceStatus != null ? attendanceStatus : "No attendance record for this date.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to retrieve attendance.");
        }
    }

    @PutMapping("/{userId}/attendance")
    public ResponseEntity<?> updateAttendance(@PathVariable Long userId, @RequestParam LocalDateTime date, @RequestParam Boolean status) {
        try {
            userService.recordAttendance(userId, date, status);
            return ResponseEntity.ok("Attendance updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to update attendance.");
        }
    }

    @DeleteMapping("/{userId}/attendance")
    public ResponseEntity<?> deleteAttendance(@PathVariable Long userId, @RequestParam LocalDateTime date) {
        try {
            userService.deleteAttendance(userId, date);
            return ResponseEntity.ok("Attendance deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to delete attendance.");
        }
    }
}
