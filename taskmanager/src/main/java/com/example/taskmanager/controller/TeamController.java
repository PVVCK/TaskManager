package com.example.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.entity.Team;
import com.example.taskmanager.service.TeamService;

@RestController
@RequestMapping("${api.prefix}/api/teams")
public class TeamController {

	@Autowired
	 private TeamService teamService;
	
	// Create a new team
	// POST /api/teams?name=DevTeam&description=Internal+tools
	@PostMapping
	public ResponseEntity<Team> createTeam(@RequestParam String name,
	                                       @RequestParam(required = false) String description) {
	    Team team = teamService.createTeam(name, description);
	    return ResponseEntity.ok(team);
	}

	
	
	 // Add a user to the team
    @PostMapping("/{teamId}/members/{userId}")
    public ResponseEntity<String> addMember(@PathVariable Long teamId, @PathVariable Long userId) {
        teamService.addMember(teamId, userId);
        return ResponseEntity.ok("User added to team successfully.");
    }

    // Remove a user from the team
    @DeleteMapping("/{teamId}/members/{userId}")
    public ResponseEntity<String> removeMember(@PathVariable Long teamId, @PathVariable Long userId) {
        teamService.removeMember(teamId, userId);
        return ResponseEntity.ok("User removed from team successfully.");
    }

    // Get a team by ID
    @GetMapping("/{id}")
    public ResponseEntity<Team> getTeamById(@PathVariable Long id) {
        return ResponseEntity.ok(teamService.getById(id));
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<Team>> getAllTeams() {
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    
}

