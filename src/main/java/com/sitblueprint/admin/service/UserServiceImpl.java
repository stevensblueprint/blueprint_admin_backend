package com.sitblueprint.admin.service;

import com.sitblueprint.admin.model.AuthUser;
import com.sitblueprint.admin.model.User;
import com.sitblueprint.admin.repository.UserRepository;
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
}
