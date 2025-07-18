package com.example.taskmanager.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.Team;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.enums.TaskStatus;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.TeamRepository;
import com.example.taskmanager.repository.UserRepository;
import com.example.taskmanager.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {
	
		@Autowired
	 	private TaskRepository taskRepo;
		
		@Autowired
	    private UserRepository userRepo;
		
		@Autowired
	    private TeamRepository teamRepo;
		


		 @Override
		    public Task createTask(Task task, Long creatorId, Long teamId) {
		        User creator = userRepo.findById(creatorId).orElseThrow(() -> new RuntimeException("Creator not found"));
		        Team team = teamRepo.findById(teamId).orElseThrow(() -> new RuntimeException("Team not found"));
		        task.setCreatedBy(creator);
		        task.setTeam(team);
		        task.setStatus(TaskStatus.OPEN);
		        task.setCreatedAt(LocalDateTime.now());
		        return taskRepo.save(task);
		    }

		    @Override
		    public Task updateTask(Long taskId, String title, String description, LocalDateTime dueDate) {
		        Task task = taskRepo.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
		        task.setTitle(title);
		        task.setDescription(description);
		        task.setDueDate(dueDate);
		        return taskRepo.save(task);
		    }

		    @Override
		    public Task assignTask(Long taskId, Long assigneeId) {
		        Task task = taskRepo.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
		        User assignee = userRepo.findById(assigneeId).orElseThrow(() -> new RuntimeException("User not found"));
		        task.setAssignee(assignee);
		        return taskRepo.save(task);
		    }


		    @Override
		    public Task markCompleted(Long taskId) {
		        Task task = taskRepo.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
		        task.setStatus(TaskStatus.COMPLETED);
		        return taskRepo.save(task);
		    }

		    @Override
		    public List<Task> getTasksForUser(Long userId) {
		        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		        return taskRepo.findByAssignee(user);
		    }

		    @Override
		    public List<Task> getTasksByStatus(Long userId, TaskStatus status) {
		        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		        return taskRepo.findByAssigneeAndStatus(user, status);
		    }

		    @Override
		    public List<Task> searchTasks(String query) {
		        return taskRepo.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
		    }

}
