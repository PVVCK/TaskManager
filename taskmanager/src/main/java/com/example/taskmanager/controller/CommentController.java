package com.example.taskmanager.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.dto.CommentRequest;
import com.example.taskmanager.entity.Comment;
import com.example.taskmanager.exception.ExecutionFailed;
import com.example.taskmanager.response.APIResponse;
import com.example.taskmanager.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("${api.prefix}/api/comments")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
		
	
	@PostMapping("/add-comment")
	public ResponseEntity<APIResponse> addComment(@Valid @RequestBody CommentRequest request, BindingResult result) {
        
		if(result.hasErrors())
		{
			StringBuilder errorMessage = new StringBuilder("Validation errors Occured: ");
			result.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append(", "));
			
			throw new ExecutionFailed(errorMessage.toString());
		}
		
		Comment comment = commentService.addComment(
                request.getTaskId(),
                request.getUserId(),
                request.getContent()
        ); 
        
		try {
		            
            APIResponse apiResponse = new APIResponse();
    		apiResponse.setSuccess(true);	
    		apiResponse.setTimestamp(LocalDateTime.now());
    		apiResponse.setData(comment);
    		apiResponse.setErrorMessage(null);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } 
		
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
    }
	

   
    @GetMapping("/task/{taskId}")
    public ResponseEntity<APIResponse> getComments(@PathVariable Long taskId) {
        try {
            
            APIResponse apiResponse = new APIResponse();
    		apiResponse.setSuccess(true);	
    		apiResponse.setTimestamp(LocalDateTime.now());
    		apiResponse.setData(commentService.getCommentsForTask(taskId));
    		apiResponse.setErrorMessage(null);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } 
		
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

    }
}
