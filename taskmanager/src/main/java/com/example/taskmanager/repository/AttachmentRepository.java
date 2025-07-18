package com.example.taskmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskmanager.entity.Attachment;
import com.example.taskmanager.entity.Task;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

	public List<Attachment> findByTask(Task task);
}
