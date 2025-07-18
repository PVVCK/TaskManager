package com.example.taskmanager.dto;

import java.time.LocalDateTime;

import com.example.taskmanager.entity.Task;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TaskRequest {
	 @NotBlank(message = "Title is required")
	    private String title;

	    private String description;

	    @NotNull(message = "Due date is required")
	    @Future(message = "Due date must be in the future")
	    private LocalDateTime dueDate;

	    @NotNull(message = "Creator ID is required")
	    private Long creatorId;

	    @NotNull(message = "Team ID is required")
	    private Long teamId;

 public Task toTask() {
     Task task = new Task();
     task.setTitle(title);
     task.setDescription(description);
     task.setDueDate(dueDate);
     return task;
 }


}
