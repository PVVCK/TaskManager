package com.example.taskmanager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taskmanager.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
	
	public  Optional<Team> findByName(String name);
	

}
