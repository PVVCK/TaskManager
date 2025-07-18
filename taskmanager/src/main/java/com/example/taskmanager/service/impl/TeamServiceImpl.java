package com.example.taskmanager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskmanager.entity.Team;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.repository.TeamRepository;
import com.example.taskmanager.repository.UserRepository;
import com.example.taskmanager.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {
	
	@Autowired
	private  TeamRepository teamRepo;
	
	@Autowired
	private UserRepository userRepo;

	

    @Override
    public Team createTeam(String name, String description) {
        Team team = new Team();
        team.setName(name);
        team.setDescription(description);
        return teamRepo.save(team);
    }

    @Override
    public void addMember(Long teamId, Long userId) {
        Team team = teamRepo.findById(teamId).orElseThrow(() -> new RuntimeException("Team not found"));
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        team.getMembers().add(user);
        teamRepo.save(team);
    }

    @Override
    public void removeMember(Long teamId, Long userId) {
        Team team = teamRepo.findById(teamId).orElseThrow(() -> new RuntimeException("Team not found"));
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        team.getMembers().remove(user);
        teamRepo.save(team);
    }

    @Override
    public List<Team> getAllTeams() {
        return teamRepo.findAll();
    }

    @Override
    public Team getById(Long id) {
        return teamRepo.findById(id).orElseThrow(() -> new RuntimeException("Team not found"));
    }

}
