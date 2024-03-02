package com.sitblueprint.admin.service;

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
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void enableUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            throw new RuntimeException("User not found with id " + userId);
        }
        User user = userOptional.get();
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
        user.setEnabled(false);
        userRepository.save(user);
    }
}
