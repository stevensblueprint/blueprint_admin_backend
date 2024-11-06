package com.sitblueprint.admin.service.users;

import com.sitblueprint.admin.model.users.Attendance;
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

    Attendance markAttendance(Long userId, LocalDateTime date, Boolean status);

    Attendance getAttendance(Long userId, LocalDateTime date);

    List<Attendance> getAllAttendance(Long userId, LocalDateTime startDate, LocalDateTime endDate);

    Attendance updateAttendance(Long userId, LocalDateTime date, Boolean status);

    void deleteAttendance(Long userId, LocalDateTime date);
}
