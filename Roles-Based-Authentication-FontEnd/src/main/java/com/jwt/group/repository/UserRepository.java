package com.jwt.group.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jwt.group.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	@Query("SELECT u from User u Where u.username = :username")
	public User getUserByUsername(@Param("username") String username);
	
}
