package com.sitblueprint.admin.model.blogs;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "blogs")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String author;
    private String title;

    @Column(nullable = false)
    private LocalDateTime dateCreated;
}
