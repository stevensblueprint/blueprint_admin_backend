package com.sitblueprint.admin.model.users;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "teams")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<User> members = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_lead_id")
    private User teamLead;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_manager_id")
    private User productManager;

    @Column(nullable = false)
    private LocalDateTime dateCreated;

    private int teamClass;
}
