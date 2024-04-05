package com.sitblueprint.admin.repository.users;

import com.sitblueprint.admin.model.users.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRecordRepository extends JpaRepository<Attendance, Long> {
}
