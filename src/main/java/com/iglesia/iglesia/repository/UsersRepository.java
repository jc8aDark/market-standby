package com.iglesia.iglesia.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.iglesia.iglesia.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
	Users findByUserName(String userName);
}
