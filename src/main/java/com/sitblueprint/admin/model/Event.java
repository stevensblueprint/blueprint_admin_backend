package com.sitblueprint.admin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String location;

	private String eventTime;

	private LocalDate date;

	@ManyToMany
	@JoinTable(name = "event_members", joinColumns = @JoinColumn(name = "event_id"), inverseJoinColumns = @JoinColumn(name = "member_id"))
	private List<Member> organizers;

	@Enumerated(EnumType.STRING)
	private EventType eventType;

	public LocalDate getDate() {
		return date;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setEventTime(String eventTime) {
		this.eventTime = eventTime;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLocation() {
		return location;
	}

	public String getEventTime() {
		return eventTime;
	}
}
