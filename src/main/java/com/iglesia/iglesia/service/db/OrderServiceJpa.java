package com.iglesia.iglesia.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.iglesia.iglesia.model.Order;
import com.iglesia.iglesia.repository.OrderRepository;
import com.iglesia.iglesia.service.IOrders;

@Service
public class OrderServiceJpa implements IOrders {
	
	@Autowired
	private OrderRepository orderRepos;
	
	@Override
	public List<Order> buscarTodas() {
		return orderRepos.findAll();
	}

	@Override
	public void guardar(Order order) {
		orderRepos.save(order);
	}

	@Override
	public List<Order> buscarByExample(Example<Order> example) {
		return orderRepos.findAll(example);
	}

	@Override
	public Order findById(Integer id) {
		 Optional<Order> optionalOrder = orderRepos.findById(id);
		return optionalOrder.orElse(null);
	}

	@Override
	public void eliminar(Integer id) {
		orderRepos.deleteById(id);
	}

}
