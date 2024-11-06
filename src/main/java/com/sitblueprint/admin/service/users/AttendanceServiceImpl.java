package com.sitblueprint.admin.service.users;

import com.sitblueprint.admin.model.users.AuthUser;
import com.sitblueprint.admin.model.users.User;
import com.sitblueprint.admin.repository.users.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final AuthApiService authApiService;

    public UserServiceImpl(UserRepository userRepository, AuthApiService authApiService) {
        this.userRepository = userRepository;
        this.authApiService = authApiService;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new NoSuchElementException("User with id " + userId + " was not found")
        );
    }

    @Override
    public User createUser(User user) {
        user.setDateJoined(LocalDateTime.now());
        AuthUser authUser = new AuthUser(user);
        try {
            authApiService.createAuthUser(authUser);
        } catch (Exception e) {
            log.error("Failed to create Auth user for username: {}", user.getUsername(), e.getMessage());
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

    @Transactional
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
