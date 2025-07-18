package com.example.taskmanager.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TaskUpdateRequest {
 private String title;
 private String description;
 private LocalDateTime
 dueDate;


}

