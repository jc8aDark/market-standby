package com.iglesia.iglesia.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;


import com.iglesia.iglesia.model.Users;
import com.iglesia.iglesia.repository.UsersRepository;
import com.iglesia.iglesia.service.IUsers;

@Service
public class UsersServiceJpa implements IUsers {

	@Autowired

	private UsersRepository userRepos;

	@Override
	public List<Users> buscarTodas() {
		return userRepos.findAll();
	}

	@Override
	public void guardar(Users user) {
		userRepos.save(user);
	}

	@Override
	public void eliminar(Integer id) {
		userRepos.deleteById(id);

	}

	@Override
	public Users buscarPorId(Integer id) {
		Optional<Users> optional = userRepos.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public List<Users> buscarByExample(Example<Users> example) {
		return userRepos.findAll(example);
	}

	@Override
	public Users buscarPorUsername(String userName) {
		return userRepos.findByUserName(userName);
	}

	

	
	
	


}
