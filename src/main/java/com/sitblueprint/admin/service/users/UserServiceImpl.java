package com.sitblueprint.admin.service.users;

import com.sitblueprint.admin.model.users.AuthUser;
import com.sitblueprint.admin.model.users.User;
import com.sitblueprint.admin.model.users.Attendance;
import com.sitblueprint.admin.repository.users.UserRepository;
import com.sitblueprint.admin.repository.users.AttendanceRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthApiService authApiService;

    @Autowired
    AttendanceRecordRepository attendanceRecordRepository;
    
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public User createUser(User user) {
        user.setDateJoined(LocalDateTime.now());
        AuthUser authUser = new AuthUser(user);

        try {
            authApiService.createAuthUser(authUser);
        } catch (Exception e) {
            throw new RuntimeException("Auth API failed to create user " + user.getUsername());
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        try {
            authApiService.updateAuthUser(new AuthUser(user));
        } catch (Exception e) {
            throw new RuntimeException("Auth API failed to update user " + user.getUsername());
        }
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUserById(Long userId) {
        Optional<User> optionalUserToDelete = userRepository.findById(userId);
        if (optionalUserToDelete.isEmpty()) {
            throw new RuntimeException("User with id " + userId + "was not found");
        }
        User userToDelete = optionalUserToDelete.get();
        try {
            authApiService.deleteAuthUser(userToDelete.getUsername());
        } catch (Exception e) {
            throw new RuntimeException("Auth API failed to delete user " + userToDelete.getId());
        }
        userRepository.deleteById(userId);
    }

    @Override
    public void enableUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found with id " + userId);
        }
        User user = userOptional.get();
        try {
            authApiService.enableAuthUser(user.getUsername());
        } catch (Exception e) {
            throw new RuntimeException("Authentication API failed to enable user " + user.getUsername());
        }
        user.setEnabled(true);
        userRepository.save(user);
    }

    @Override
    public void disableUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found with id " + userId);
        }
        User user = userOptional.get();
        try {
            authApiService.disableAuthUser(user.getUsername());
        } catch (Exception e) {
            throw new RuntimeException("Authentication API failed to disable user " + user.getUsername());
        }
        user.setEnabled(false);
        userRepository.save(user);
    }

    @Override
    public void resetPassword(Long userId, String newPassword) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found with id " + userId);
        }
        User user = userOptional.get();
        user.setPassword(newPassword);
        try {
            authApiService.resetPasswordAuthUser(user.getUsername(), newPassword);
        } catch (Exception e) {
            throw new RuntimeException("Auth API failed to reset password of user " + user.getUsername());
        }
    }

    @Override
    public Attendance getAttendanceById (Long attendanceId){
    	try {
		Attendance attendance = attendanceRecordRepository.findById(attendanceId).get();
	} catch (Exception e) {
		throw new RuntimeException("Attendance not found with id " + attendanceId);
	}
    }
	
    @Override
    public Attendance createAttendance(Attendance attendance){
    	attendance.setDate(LocalDateTime.now());
	return attendanceRecordRepository.save(attendance);
    }

    @Override
    public List<Attendance> getUserAttendances(Long teamId, Long memberId) {
    	return NULL;
    }

    @Override
    public Attendance updateAttendance (Long attendanceId, Attendance attendance){
    	return NULL;
    }

    @Override
    public Attendance deleteAttendance (Long attendanceId) {
    	return NULL;
    }
}
