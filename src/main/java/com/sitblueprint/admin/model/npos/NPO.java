package com.sitblueprint.admin.model.npos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sitblueprint.admin.model.users.Team;

import java.time.LocalDate;

@Entity
@Table(name = "npos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NPO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team teamAssigned;

    private String url;

    @Column(nullable = false)
    private LocalDate dateOfRecruitment;
}
