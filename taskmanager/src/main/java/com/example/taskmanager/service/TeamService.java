package com.example.taskmanager.service;

import java.util.List;

import com.example.taskmanager.entity.Team;

public interface TeamService {
	
	Team createTeam(String name, String description);
    void addMember(Long teamId, Long userId);
    void removeMember(Long teamId, Long userId);
    List<Team> getAllTeams();
    Team getById(Long id);

}
