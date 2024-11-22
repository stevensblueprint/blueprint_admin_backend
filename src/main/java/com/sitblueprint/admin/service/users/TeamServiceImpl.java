package com.sitblueprint.admin.service.users;

import com.sitblueprint.admin.model.users.Team;
import com.sitblueprint.admin.model.users.Attendance;
import com.sitblueprint.admin.model.users.User;
import com.sitblueprint.admin.repository.users.TeamRepository;
import com.sitblueprint.admin.repository.users.AttendanceRepository;
import com.sitblueprint.admin.repository.users.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;

    public TeamServiceImpl(TeamRepository teamRepository, AttendanceRepository attendanceRepository, UserRepository userRepository) {
        this.teamRepository = teamRepository;
        this.attendanceRepository = attendanceRepository;
        this.userRepository = userRepository;
    }
    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    @Override
    public Team getTeamById(Long teamId) {
        return teamRepository.findById(teamId).orElse(null);
    }

    @Override
    public Team createTeam(Team team) {
        team.setDateCreated(LocalDateTime.now());
        return teamRepository.save(team);
    }

    @Override
    public Team updateTeam(Team team) {
        return teamRepository.saveAndFlush(team);
    }

    @Override
    public void deleteTeam(Long teamId) {
        teamRepository.deleteById(teamId);
    }

    @Override
    public User getTeamLeadById(Long teamId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        if (!optionalTeam.isPresent()) {
            throw new RuntimeException("Team not found: " + teamId);
        }
        return optionalTeam.get().getTeamLead();
    }

    @Override
    public User getProductManagerById(Long teamId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        if (!optionalTeam.isPresent()) {
            throw new RuntimeException("Team not found: " + teamId);
        }
        return optionalTeam.get().getProductManager();
    }

    @Override
    public List<Attendance> markTeamAttendance(Long teamId, LocalDateTime date) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new NoSuchElementException("Team not found"));

        List<User> teamMembers = new ArrayList<>(team.getMembers());
        List<Attendance> attendanceList = new ArrayList<>();

        for (User member : teamMembers) {
            Optional<Attendance> existingAttendance = attendanceRepository.findByUserIdAndDate(member.getId(), date);

            if (existingAttendance.isEmpty()) {
                Attendance attendance = new Attendance(null, date, member);
                attendanceList.add(attendanceRepository.save(attendance));
            }
        }
        return attendanceList;
    }

    @Override
    public List<Attendance> getTeamAttendance(Long teamId, LocalDateTime date) {
        return attendanceRepository.findAllByTeamIdAndDate(teamId, date);
    }

    @Override
    public List<Attendance> getTeamAllAttendance(Long teamId, LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate != null && endDate != null) {
            return attendanceRepository.findAllByTeamIdAndDateBetween(teamId, startDate, endDate);
        } else if (startDate != null) {
            return attendanceRepository.findAllByTeamIdAndDateAfter(teamId, startDate);
        } else if (endDate != null) {
            return attendanceRepository.findAllByTeamIdAndDateBefore(teamId, endDate);
        } else {
            return attendanceRepository.findAllByTeamId(teamId);
        }
    }

    @Override
    public List<Attendance> updateTeamAttendance(Long teamId, LocalDateTime date) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new NoSuchElementException("Team not found"));

        List<User> teamMembers = new ArrayList<>(team.getMembers());
        List<Attendance> attendanceList = new ArrayList<>();

        for (User member : teamMembers) {
            Optional<Attendance> existingAttendance = attendanceRepository.findByUserIdAndDate(member.getId(), date);

            if (existingAttendance.isEmpty()) {
                Attendance attendance = new Attendance(null, date, member);
                attendanceList.add(attendanceRepository.save(attendance));
            } else {
                attendanceRepository.delete(existingAttendance.get());
            }
        }

        return attendanceList;
    }

    @Override
    public void deleteTeamAttendance(Long teamId, LocalDateTime date) {
        List<Attendance> attendances = attendanceRepository.findAllByTeamIdAndDate(teamId, date);

        if (attendances.isEmpty()) {
            throw new NoSuchElementException("No attendance records found.");
        }

        attendanceRepository.deleteAll(attendances);
    }
}