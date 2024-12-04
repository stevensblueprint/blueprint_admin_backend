package com.sitblueprint.admin.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiError {
	private LocalDateTime timestamp;
	private int status;
	private String error;
	private String message;
	private String path;
}
