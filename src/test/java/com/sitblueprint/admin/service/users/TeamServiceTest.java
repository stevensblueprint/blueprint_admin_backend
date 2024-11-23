package com.sitblueprint.admin.service.users;

import com.sitblueprint.admin.model.users.Team;
import com.sitblueprint.admin.repository.users.AttendanceRepository;
import com.sitblueprint.admin.repository.users.TeamRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {
  @Mock
  TeamRepository teamRepository;

  @Mock
  AttendanceRepository attendanceRepository;

  private TeamService teamService;
  private Team testTeam;
}
