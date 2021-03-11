package es.sixshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import es.sixshop.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userR;
	
}
