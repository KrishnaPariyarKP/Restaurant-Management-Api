package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserInfo;
import com.example.demo.repository.UserInfoRepository;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserInfoRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<UserInfo> userDetail = repository.findByUsername(username); // Assuming 'email' is used as username
//
//        // Converting UserInfo to UserDetails
//        return userDetail.map(UserInfoDetails::new)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    	
//    	return repository.findByUsername(username)
//                .map(user -> User.builder().username(user.getEmail())
//                        .password(user.getPassword())
//                        .build())
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    	
    	return repository.findByUsername(username) // Query the database by 'username'
                .map(UserInfoDetails::new) // Map the result to UserInfoDetails
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    public String addUser(UserInfo userInfo) {
        // Encode password before saving the user
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "User Added Successfully";
    }
}