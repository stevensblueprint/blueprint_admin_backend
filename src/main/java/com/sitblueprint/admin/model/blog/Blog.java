package com.sitblueprint.admin.model.blog;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "blogs")
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDateTime datePublished;

    private LocalDateTime lastModified;

    private String status; // Published, In Progress, Deleted

    private String pathToMarkdown;
}
