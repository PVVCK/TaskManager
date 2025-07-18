package com.example.taskmanager.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.taskmanager.dto.LoginRequest;
import com.example.taskmanager.dto.UserRegistrationRequest;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.exception.ExecutionFailed;
import com.example.taskmanager.response.APIResponse;
import com.example.taskmanager.security.CustomUserDetails;
import com.example.taskmanager.security.JWTUtil;
import com.example.taskmanager.service.UserService;
import org.springframework.security.core.Authentication;



import jakarta.validation.Valid;

@RestController
@RequestMapping("${api.prefix}/api/user")
public class UserController {
	
	@Autowired private UserService userService;
	@Autowired private PasswordEncoder passwordEncoder;
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JWTUtil jwtUtil;
	
	 // Register a new user
    @PostMapping("/register")
    public ResponseEntity<APIResponse> register(@Valid @RequestBody UserRegistrationRequest request, BindingResult result) {
       
        if(result.hasErrors())
		{
			StringBuilder errorMessage = new StringBuilder("Validation errors Occured: ");
			result.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append(", "));
			
			throw new ExecutionFailed(errorMessage.toString());
		}
        
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // Hash password
        user.setRole(com.example.taskmanager.enums.Role.USER); // default role

		try {
		            
            APIResponse apiResponse = new APIResponse();
    		apiResponse.setSuccess(true);	
    		apiResponse.setTimestamp(LocalDateTime.now());
    		apiResponse.setData(userService.register(user));
    		apiResponse.setErrorMessage(null);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } 
		
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
    }

    
    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@Valid @RequestBody LoginRequest request, BindingResult result) {
    	 if(result.hasErrors())
 		{
 			StringBuilder errorMessage = new StringBuilder("Validation errors Occured: ");
 			result.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append(", "));
 			
 			throw new ExecutionFailed(errorMessage.toString());
 		}
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            String token = jwtUtil.generateToken(
                    userDetails.getUsername(),
                    userDetails.getAuthorities().iterator().next().getAuthority()
            );

            APIResponse apiResponse = new APIResponse();
			apiResponse.setSuccess(true);
			apiResponse.setTimestamp(LocalDateTime.now());
			apiResponse.setData("Token :- " + token);
			
			return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

        }
        catch (BadCredentialsException ex) {
        	
        	 APIResponse apiResponse = new APIResponse();
 			apiResponse.setSuccess(true);
 			apiResponse.setTimestamp(LocalDateTime.now());
 			apiResponse.setErrorMessage("Invalid email or password");
 			
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(apiResponse);
        }
    }


	@GetMapping("/{id}")
    public ResponseEntity<APIResponse> getUser(@PathVariable Long id) {
       
        
        try {
			APIResponse apiResponse = new APIResponse();
			apiResponse.setSuccess(true);
			apiResponse.setTimestamp(LocalDateTime.now());
			apiResponse.setData(userService.getById(id));
			
			
			return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
    }

    // Update profile
    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> updateProfile(@PathVariable Long id, @RequestBody User updatedData, BindingResult result) {
        User updated = userService.updateUser(id, updatedData.getName(), updatedData.getEmail());
       
        
        if(result.hasErrors())
		{
			StringBuilder errorMessage = new StringBuilder("Validation errors Occured: ");
			result.getAllErrors().forEach(error -> errorMessage.append(error.getDefaultMessage()).append(", "));
			
			throw new ExecutionFailed(errorMessage.toString());
		}
        
        try {
            
            APIResponse apiResponse = new APIResponse();
    		apiResponse.setSuccess(true);	
    		apiResponse.setTimestamp(LocalDateTime.now());
    		apiResponse.setData(updated);
    		apiResponse.setErrorMessage(null);
            return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
        } 
		
		catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
    }

}
