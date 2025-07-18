package com.example.taskmanager.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskmanager.entity.Comment;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.repository.CommentRepository;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;
import com.example.taskmanager.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
		@Autowired
		private CommentRepository commentRepo;
		@Autowired
	    private TaskRepository taskRepo;
		@Autowired
	    private UserRepository userRepo;


		@Override
	    public Comment addComment(Long taskId, Long userId, String content) {
	        Task task = taskRepo.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
	        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

	        Comment comment = new Comment();
	        comment.setTask(task);
	        comment.setAuthor(user);
	        comment.setContent(content);
	        comment.setCreatedAt(LocalDateTime.now());
	        return commentRepo.save(comment);
	    }

	    @Override
	    public List<Comment> getCommentsForTask(Long taskId) {
	        Task task = taskRepo.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
	        return commentRepo.findByTask(task);
	    }

}
