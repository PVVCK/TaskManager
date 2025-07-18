package com.example.taskmanager.dto;

import java.time.LocalDateTime;

import com.example.taskmanager.enums.TaskStatus;

import lombok.Data;

@Data
public class TaskResponse {

	private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDateTime dueDate;

}
