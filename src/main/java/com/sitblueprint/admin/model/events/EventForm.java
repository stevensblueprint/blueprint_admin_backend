package com.sitblueprint.admin.model.events;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "event_forms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String email;
    private LocalDateTime dateCreated;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;
}
