package com.example.taskmanager.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.taskmanager.entity.Attachment;

public interface AttachmentService {
	
	 Attachment uploadAttachment(Long taskId, Long userId, MultipartFile file);
	    List<Attachment> getAttachmentsForTask(Long taskId);

}
