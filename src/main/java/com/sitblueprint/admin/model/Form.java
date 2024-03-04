package com.sitblueprint.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Form {
    private String name;
    private String email;
    private LocalDateTime dateCreated;
}
