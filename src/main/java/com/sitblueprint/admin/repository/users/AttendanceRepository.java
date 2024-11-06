package com.sitblueprint.admin.repository.users;

import com.sitblueprint.admin.model.users.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    List<Attendance> findAllByUserId(Long userId);
    Optional<Attendance> findByUserIdAndDate(Long userId, LocalDateTime date);
    List<Attendance> findAllByUserIdAndDateBetween(Long userId, LocalDateTime startDate, LocalDateTime endDate);
    List<Attendance> findAllByUserIdAndDateAfter(Long userId, LocalDateTime startDate);
    List<Attendance> findAllByUserIdAndDateBefore(Long userId, LocalDateTime endDate);
    List<Attendance> findAllByTeamId(Long teamId);
    List<Attendance> findAllByTeamIdAndDate( Long teamId, LocalDateTime date);
    List<Attendance> findAllByTeamIdAndDateBetween(Long teamId, LocalDateTime startDate, LocalDateTime endDate);
    List<Attendance> findAllByTeamIdAndDateAfter(Long teamId, LocalDateTime startDate);
    List<Attendance> findAllByTeamIdAndDateBefore(Long teamId, LocalDateTime endDate);
}