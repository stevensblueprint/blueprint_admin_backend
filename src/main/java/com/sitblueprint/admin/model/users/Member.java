package com.sitblueprint.admin.model.users;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sitblueprint.admin.dtos.member.MemberDTO;
import com.sitblueprint.admin.dtos.member.OrganizationSummaryDTO;
import com.sitblueprint.admin.dtos.member.RoleDTO;
import com.sitblueprint.admin.dtos.member.TeamSummaryDTO;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.*;

import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "members")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@JsonIdentityInfo(
    generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id"
)
public class Member implements UserDetails {
    private static final Map<String, Integer> roleRanking = Map.of(
        "E-BOARD", 1,
        "TEAM_LEAD", 2,
        "PRODUCT_MANAGER", 3,
        "DEVELOPER", 3,
        "DESIGNER", 4,
        "BLUEPRINT_INTERNAL_TEAM", 5
    );

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean isActive;

    @Column(nullable = false)
    private LocalDate dateJoined;

    @ManyToMany
    @JoinTable(
        name = "member_roles",
        joinColumns = @JoinColumn(name = "member_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToMany(mappedBy = "teamLead")
    private Set<Organization> teamLeadOrganizations;

    @OneToMany(mappedBy = "projectManager")
    private Set<Organization> managedOrganizations;

    @OneToMany(mappedBy = "teamLead")
    private Set<Team> leadTeams;

    @OneToMany(mappedBy = "projectManager")
    private Set<Team> managedTeams;

    @OneToMany(mappedBy = "designer")
    private Set<Team> designedTeams;

    public MemberDTO toDTO() {
        MemberDTO memberDTO = MemberDTO.builder()
            .id(this.id)
            .name(this.name)
            .username(this.username)
            .email(this.email)
            .dateJoined(this.dateJoined)
            .isActive(this.isActive)
            .build();
        Set<RoleDTO> roleDTOS = this.roles.stream()
            .map(role -> RoleDTO.builder()
               .id(role.getId())
               .name(role.getName())
               .build())
            .collect(Collectors.toSet());
        memberDTO.setRoles(roleDTOS);

        if (this.team != null) {
            TeamSummaryDTO teamSummaryDTO = TeamSummaryDTO
                .builder()
                .id(this.team.getId())
                .name(this.team.getName())
                .build();

            if (this.team.getOrganization() != null) {
                OrganizationSummaryDTO organizationSummaryDTO = OrganizationSummaryDTO
                    .builder()
                    .id(this.team.getOrganization().getId())
                    .name(this.team.getOrganization().getName())
                    .build();
                teamSummaryDTO.setOrganization(organizationSummaryDTO);
            }
            memberDTO.setTeam(teamSummaryDTO);
        }
        return memberDTO;
    }

    private String getHighestRankedRole() {
        return this.roles.stream()
           .max(Comparator.comparingInt(roleRanking::get))
           .map(Role::getName)
           .orElse(null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String highestRole = getHighestRankedRole();
        if (highestRole == null) {
            return List.of();
        }
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(highestRole);
        return Collections.singletonList(authority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.isActive;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive;
    }
}

