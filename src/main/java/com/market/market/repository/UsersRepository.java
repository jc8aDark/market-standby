package com.market.market.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.market.market.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
	Users findByUserName(String userName);
}
