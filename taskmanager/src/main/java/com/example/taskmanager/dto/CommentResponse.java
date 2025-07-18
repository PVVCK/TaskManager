package com.example.taskmanager.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class CommentResponse {

	   private Long id;
	    private String content;
	    private String userName;
	    private LocalDateTime timestamp;

}
