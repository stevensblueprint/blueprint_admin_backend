package com.sitblueprint.admin.controller;

import com.sitblueprint.admin.model.users.Attendance;
import com.sitblueprint.admin.service.users.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/role/")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;
}
