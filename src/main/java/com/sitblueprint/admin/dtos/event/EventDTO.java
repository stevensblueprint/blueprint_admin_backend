package com.sitblueprint.admin.dtos.event;

import com.sitblueprint.admin.dtos.member.MemberDTO;
import com.sitblueprint.admin.model.Event;
import com.sitblueprint.admin.model.EventType;
import com.sitblueprint.admin.model.Member;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
	private Long id;
	private String name;
	private String location;
	private String eventTime;
	private LocalDate date;
	private List<MemberDTO> organizers;
	private EventType eventType;

	public Event toEntity() {
		Event event = Event.builder().id(this.id).name(this.name).location(this.location).eventTime(this.eventTime)
				.date(this.date).eventType(this.eventType).build();
		if (this.organizers != null) {
			List<Member> organizers = this.organizers.stream().map(MemberDTO::toEntity).collect(Collectors.toList());
			event.setOrganizers(organizers);
		}
		return event;
	}
}
