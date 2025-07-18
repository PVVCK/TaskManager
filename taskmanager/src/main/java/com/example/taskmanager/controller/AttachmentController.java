package com.example.taskmanager.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.taskmanager.entity.Attachment;
import com.example.taskmanager.response.APIResponse;
import com.example.taskmanager.service.AttachmentService;

import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("${api.prefix}/api/attachments")
public class AttachmentController {

	@Autowired
	 private AttachmentService attachmentService;

	 @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	 public ResponseEntity<APIResponse> uploadAttachment(
			 @RequestParam @NotNull(message = "Task ID is required") Long taskId,
	            @RequestParam @NotNull(message = "User ID is required") Long userId,
	            @RequestParam("file") @NotNull(message = "File is required") MultipartFile file)
	 {
		 
		  Attachment attachment = attachmentService.uploadAttachment(taskId, userId, file);
		 
	        
	        try {
	            
	            APIResponse apiResponse = new APIResponse();
	    		apiResponse.setSuccess(true);	
	    		apiResponse.setTimestamp(LocalDateTime.now());
	    		apiResponse.setData(attachment);
	    		apiResponse.setErrorMessage(null);
	            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	        } 
			
			catch (Exception e) {
				// TODO: handle exception
				throw e;
			}
	      
	        
	    }
	 
	 @GetMapping("/task/{taskId}")
	    public ResponseEntity<APIResponse> getAttachmentsForTask(@PathVariable Long taskId) {
	        
		 try {
	            
	            APIResponse apiResponse = new APIResponse();
	    		apiResponse.setSuccess(true);	
	    		apiResponse.setTimestamp(LocalDateTime.now());
	    		apiResponse.setData(attachmentService.getAttachmentsForTask(taskId));
	    		apiResponse.setErrorMessage(null);
	            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
	        } 
			
			catch (Exception e) {
				// TODO: handle exception
				throw e;
			}
	    }
	 
}
