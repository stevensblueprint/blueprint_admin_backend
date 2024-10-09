package com.sitblueprint.admin.model.npos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sitblueprint.admin.model.users.Team;

public class NPO {
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn()
    private Team team_assigned;




}
