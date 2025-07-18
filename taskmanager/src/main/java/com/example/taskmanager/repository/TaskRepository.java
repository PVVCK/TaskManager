package com.example.taskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.Team;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.enums.TaskStatus;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	   public List<Task> findByAssignee(User assignee);
	   public List<Task> findByTeam(Team team);
	   public List<Task> findByAssigneeAndStatus(User assignee, TaskStatus status);
	   public List<Task> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);
}
