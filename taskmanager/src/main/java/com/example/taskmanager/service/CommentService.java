package com.example.taskmanager.service;

import java.util.List;

import com.example.taskmanager.entity.Comment;

public interface CommentService {

	 Comment addComment(Long taskId, Long userId, String content);
	 List<Comment> getCommentsForTask(Long taskId);
}
