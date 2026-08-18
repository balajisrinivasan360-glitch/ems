package com.tcs.ems.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.ems.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

Optional<User> getByEmail(String email);
		
}
