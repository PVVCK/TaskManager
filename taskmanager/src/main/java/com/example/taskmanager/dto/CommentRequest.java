package com.example.taskmanager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentRequest {

	    @NotNull(message = "Task ID is required")
	    private Long taskId;

	    @NotNull(message = "User ID is required")
	    private Long userId;

	    @NotBlank(message = "Comment content cannot be empty")
	    private String content;
}
