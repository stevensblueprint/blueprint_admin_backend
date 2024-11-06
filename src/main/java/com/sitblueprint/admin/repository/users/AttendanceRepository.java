package com.sitblueprint.admin.repository.users;

import com.sitblueprint.admin.model.users.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<Attendance> findByUserIdAndDate(Long userId, LocalDateTime date);
    List<Attendance> findAllByUserId(Long userId);
}