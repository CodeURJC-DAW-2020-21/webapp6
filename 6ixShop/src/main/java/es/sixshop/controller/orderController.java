package es.sixshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.sixshop.repository.UserRepository;
import es.sixshop.service.OrderService;

@Controller
public class orderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private UserRepository userR;

}
