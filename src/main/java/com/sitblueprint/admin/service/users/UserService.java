package com.sitblueprint.admin.service.users;

import com.sitblueprint.admin.model.users.User;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long userId);

    User createUser(User user);

    User updateUser(User user);

    void deleteUserById(Long userId);

    void enableUserById(Long userId);

    void disableUserById(Long userId);

    void resetPassword(Long userId, String password);

    void recordAttendance(Long userId, LocalDateTime date, Boolean status);

    Boolean getAttendance(Long userId, LocalDateTime date);

    void deleteAttendance(Long userId, LocalDateTime date);
}
