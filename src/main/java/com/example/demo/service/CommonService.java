package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserInfo;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.security.JwtService;
import com.example.demo.security.UserInfoService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class CommonService {
	

	@Autowired
	JwtService jwtService;
	
	@Autowired
	UserInfoRepository userInfoRepositoryf;
	
	public Optional<UserInfo> userdto(HttpServletRequest request) {
		 String authorizationHeader = request.getHeader("Authorization");
	        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
	            String token = authorizationHeader.replace("Bearer ", "");
	    		String username = jwtService.extractUsername(token);
	    		return  userInfoRepositoryf.findByUsername(username);
	        }
	        return null;
	}
	

}
