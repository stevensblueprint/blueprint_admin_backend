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
    Optional<Attendance> findByUserIdAndDate(@Param("userId") Long userId, @Param("date") LocalDateTime date);
    List<Attendance> findAllByTeamId(@Param("teamId") Long teamId);
    List<Attendance> findAllByTeamIdAndDate(@Param("teamId") Long teamId, @Param("date") LocalDateTime date);
    List<Attendance> findAllByUserId(@Param("userId") Long userId);
}