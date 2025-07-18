package com.example.taskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskmanager.entity.Comment;
import com.example.taskmanager.entity.Task;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	public List<Comment> findByTask(Task task);

}
