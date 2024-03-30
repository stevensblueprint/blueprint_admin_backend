package com.sitblueprint.admin.controller;

import com.sitblueprint.admin.model.users.Attendance;
import com.sitblueprint.admin.service.users.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/attendance/")
public class AttendanceController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<Attendance> getAttendanceByID(@Param("attendanceId") String attendanceId) {
        return userService.getUserAttendance(Long.parseLong(attendanceId));
    }

    @GetMapping
    public List<Attendance> getTeamAttendance(@Param("teamId") String teamId) {
	//returns a list of attendances of a particular team
        return userService.getTeamAttendance(Long.parseLong(teamId));
    }

    @PostMapping
    public Long createAttendance(@RequestBody Attendance attendance) {
        //creates attendance object and fill its fields. returns the attendance ID and https response code
	return userService.createAttendance(attendance);
    }

    @PutMapping
    public Long updateAttendance(@RequestBody Attendance attendance) {
	//makes attendance object fills fields but with ID supplied. return attendance ID and https response
        return userService.updateAttendance(attendance);
    }

    @DeleteMapping
    public Long deleteAttendance(String attendanceId) {
	//removes attendance object from database and returns the id along with https response
        userService.deleteAttendanceById(Long.parseLong(attendanceId));
    }

}
