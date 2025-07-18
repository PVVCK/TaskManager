package com.example.taskmanager.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.enums.TaskStatus;

public interface TaskService {

	  	Task createTask(Task task, Long creatorId, Long teamId);
	    Task updateTask(Long taskId, String title, String description, LocalDateTime dueDate);
	    Task assignTask(Long taskId, Long assigneeId);
	    Task markCompleted(Long taskId);
	    List<Task> getTasksForUser(Long userId);
	    List<Task> getTasksByStatus(Long userId, TaskStatus status);
	    List<Task> searchTasks(String query);
}
