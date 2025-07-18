/**
 * 
 */
package com.example.taskmanager.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.taskmanager.entity.Attachment;
import com.example.taskmanager.entity.Task;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.repository.AttachmentRepository;
import com.example.taskmanager.repository.TaskRepository;
import com.example.taskmanager.repository.UserRepository;
import com.example.taskmanager.service.AttachmentService;

@Service
public class AttachmentServiceImpl implements AttachmentService {
	
	@Autowired
	private AttachmentRepository attachmentRepo;
	@Autowired
    private TaskRepository taskRepo;
	@Autowired
    private UserRepository userRepo;

	 @Override
	    public Attachment uploadAttachment(Long taskId, Long userId, MultipartFile file) {
	        Task task = taskRepo.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
	        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

	        // Placeholder: store to disk / S3 / DB
	        String filename = file.getOriginalFilename();
	        String fakeUrl = "https://files.example.com/" + UUID.randomUUID() + "-" + filename;

	        Attachment attachment = new Attachment();
	        attachment.setTask(task);
	        attachment.setUploadedBy(user);
	        attachment.setFilename(filename);
	        attachment.setUrl(fakeUrl);
	        attachment.setUploadedAt(LocalDateTime.now());
	        return attachmentRepo.save(attachment);
	    }

	    @Override
	    public List<Attachment> getAttachmentsForTask(Long taskId) {
	        Task task = taskRepo.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
	        return attachmentRepo.findByTask(task);
	    }

}
