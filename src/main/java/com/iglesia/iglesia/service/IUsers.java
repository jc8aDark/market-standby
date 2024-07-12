package com.iglesia.iglesia.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.iglesia.iglesia.model.Users;

@Service
public interface IUsers {
	List<Users> buscarTodas();
	void guardar(Users user);
	void eliminar(Integer id);
	Users buscarPorId(Integer id);
	List<Users> buscarByExample(Example<Users> example);
	Users buscarPorUsername(String userName);

}
