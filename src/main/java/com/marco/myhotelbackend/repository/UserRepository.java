package com.marco.myhotelbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.marco.myhotelbackend.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("SELECT us FROM User us WHERE us.userName=:username")
	public User getUserByUsername(@Param("username") String username);
	
}
