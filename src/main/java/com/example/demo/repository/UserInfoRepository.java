package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserInfo;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
	
	@Query("select s from UserInfo s where s.username = ?1 ")
    Optional<UserInfo> findByUsername(String username); // Use 'email' if that is the correct field for login
}