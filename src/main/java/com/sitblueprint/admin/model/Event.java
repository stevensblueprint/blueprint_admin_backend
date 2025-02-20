package com.sitblueprint.admin.model;


import com.sitblueprint.admin.dtos.event.EventDTO;
import com.sitblueprint.admin.dtos.member.MemberDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "events")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

	public EventDTO toDTO() {
		EventDTO eventDTO = EventDTO.builder().id(this.id).name(this.name).location(this.location)
				.eventTime(this.eventTime).date(this.date).eventType(this.eventType).build();

		if (this.organizers != null) {
			List<MemberDTO> organizers = this.organizers.stream().map(Member::toDTO).collect(Collectors.toList());
			eventDTO.setOrganizers(organizers);
		}

		return eventDTO;
	}
}
