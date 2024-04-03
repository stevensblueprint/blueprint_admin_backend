package com.sitblueprint.admin.controller;

import com.sitblueprint.admin.model.users.Attendance;
import com.sitblueprint.admin.service.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users/{userId}/attendance")
public class AttendanceController {

    @Autowired
    UserService userService;

    @GetMapping("/{attendanceId}")
    public List<Attendance> getAttendanceByID(@PathVariable("attendanceId") String attendanceId) {
        return userService.getAttendanceById(Long.parseLong(attendanceId));
    }
	
    @GetMapping
    public List<Attendance> getUserAttendances(@RequestParam("teamId") String teamId, @RequestParam("userId") String userId) {
	//returns a list of attendances of a particular team
	//gets teamId in the impl
        return userService.getUserAttendances(Long.parseLong(teamId), Long.parseLong(userId));
    }

    @PostMapping
    public Attendance createAttendance(@RequestBody Attendance attendance) {
        //creates attendance object and fill its fields. returns the attendance ID and https response code
	//parse the JSON in the impl
	return userService.createAttendance(attendance);
    }

    @PutMapping("/{attendanceId}")
    public Attendance updateAttendance(@RequestParam("attendanceId") String attendanceId, @RequestBody Attendance attendance) {
	//makes attendance object fills fields but with ID supplied. return attendance ID and https response
        return userService.updateAttendance(Long.parseLong(attendanceId), attendance);
    }

    @DeleteMapping("/{attendanceId}")
    public Attendance deleteAttendance(@RequestParam("attendanceId") String attendanceId) {
	//removes attendance object from database and returns the id along with https response
        userService.deleteAttendance(Long.parseLong(attendanceId));
    }

}
