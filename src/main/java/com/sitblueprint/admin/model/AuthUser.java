package com.sitblueprint.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthUser {
    String displayName;
    String email;
    String password;
    Boolean disabled;
    List<String> groups;
}
