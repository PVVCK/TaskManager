package com.example.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.dto.TaskUpdateRequest;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.enums.TaskStatus;
import com.example.taskmanager.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("${api.prefix}/api/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	// Create a new task
    @PostMapping("/create-task")
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());

        Task created = taskService.createTask(task, request.getCreatorId(), request.getTeamId());
        return ResponseEntity.ok(created);
    }
    

    // Update task
    @PutMapping("/{taskId}")
    public ResponseEntity<Task> updateTask(@PathVariable Long taskId, @Valid @RequestBody TaskUpdateRequest request) {
    		Task updated = taskService.updateTask(taskId, request.getTitle(), request.getDescription(), request.getDueDate());
    		return ResponseEntity.ok(updated);
    }

    // Assign task
    @PostMapping("/{taskId}/assign/{userId}")
    public ResponseEntity<Task> assignTask(@PathVariable Long taskId, @PathVariable Long userId) {
        Task updated = taskService.assignTask(taskId, userId);
        return ResponseEntity.ok(updated);
    }

    // Mark task as completed
    @PostMapping("/{taskId}/complete")
    public ResponseEntity<Task> markCompleted(@PathVariable Long taskId) {
        Task completed = taskService.markCompleted(taskId);
        return ResponseEntity.ok(completed);
    }

    // Get all tasks for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getTasksForUser(@PathVariable Long userId) {
        return ResponseEntity.ok(taskService.getTasksForUser(userId));
    }

    // Filter tasks by status
    @GetMapping("/user/{userId}/status")
    public ResponseEntity<List<Task>> getTasksByStatus(@PathVariable Long userId,
                                                       @RequestParam TaskStatus status) {
        return ResponseEntity.ok(taskService.getTasksByStatus(userId, status));
    }

    // Search tasks by title or description
    @GetMapping("/search")
    public ResponseEntity<List<Task>> searchTasks(@RequestParam String query) {
        return ResponseEntity.ok(taskService.searchTasks(query));
    }
}
