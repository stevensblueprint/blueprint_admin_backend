package com.sitblueprint.admin.controller.exceptions;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
	private LocalDateTime timestamp;
	private int status;
	private String error;
	private String message;
	private String path;
}
