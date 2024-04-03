package com.sitblueprint.admin.model.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import java.util.Set;
import java.time.LocalDateTime;
import java.util.HashSet;

@Entity
@Table(name = "attendance")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Attendance {
	//generates ID when make isntance
	@Id
    	@GeneratedValue(strategy = GenerationType.IDENTITY)
    	private long id;
	
    	@ManyToOne(fetch = FetchType.LAZY)
    	@JoinColumn(name = "user_id")
    	private User user;
	
    	private LocalDate date;
	
	private enum Status {
		Present,
		Absent,
		Excused,
		LeftEarly,
		Late
	}
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    		private Set<Attendance> attendanceRecords = new HashSet<>();
}
