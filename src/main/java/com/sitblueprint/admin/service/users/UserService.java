package com.sitblueprint.admin.service.users;

import com.sitblueprint.admin.model.users.User;
import com.sitblueprint.admin.model.users.Attendance;
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

    Attendance getAttendanceById(Long attendanceId);

    List<Attendance> getUserAttendance(Long userId);

    List<Attendance> getTeamAttendance(Long userId);

    Attendance createAttendance(Attendance attendance);

    Attendance updateAttendance(Long attendanceId, Attendance attendance);

    Attendance deleteAttendance(String attendanceID);
}
