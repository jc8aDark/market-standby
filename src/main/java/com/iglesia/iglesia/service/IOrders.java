package com.iglesia.iglesia.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.iglesia.iglesia.model.Order;

@Service
public interface IOrders {
	List<Order> buscarTodas();
	void guardar(Order order);
	List<Order> buscarByExample(Example<Order> example);
	Order findById(Integer id);
	void eliminar(Integer id);
}
