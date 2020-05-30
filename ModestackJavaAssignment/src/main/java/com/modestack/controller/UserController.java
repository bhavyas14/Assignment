package com.modestack.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.modestack.model.Message;
import com.modestack.model.Response;
import com.modestack.model.User;
import com.modestack.service.UserService;

@RestController
@RequestMapping(value = "/")
public class UserController {
	
	public static final int POST_CODE = 201;
	public static final int GET_CODE = 200;
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "register", method = RequestMethod.POST)
	public ResponseEntity<Response> registerUser(@RequestBody User user) {
		System.out.println("Creating User " + user.getUserName());
		Message message = new Message();
		List<Message> list = new ArrayList<Message>();
		Response response = new Response();
		
		int count = userService.registerUser(user);
		if (!(count > 0)) {
			System.out.println("No user registered");
			response.setStatusCode(409);
			message.setMessage("No user created");
			list.add(message);
			response.setBody(list);
			return new ResponseEntity<Response>(response,HttpStatus.CONFLICT);
		}
		response.setStatusCode(POST_CODE);
		message.setMessage("New User created");
		list.add(message);
		response.setBody(list);
		return new ResponseEntity<Response>(response, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ResponseEntity<Response> userLogin(@RequestBody User user) {
		System.out.println("Login User " + user.getUserName());
		Message message = new Message();
		List<Message> list = new ArrayList<Message>();
		Response response = new Response();

        boolean flag = userService.isUserExist(user);
        System.out.println(flag);
        if (!flag) {
			System.out.println("No user found");
			response.setStatusCode(409);
			message.setMessage("No user found");
			list.add(message);
			response.setBody(list);
			return new ResponseEntity<Response>(response,HttpStatus.CONFLICT);
		}
		response.setStatusCode(GET_CODE);
		message.setMessage("Success");
		list.add(message);
		response.setBody(list);
		return new ResponseEntity<Response>(response, HttpStatus.CREATED);
		
	}




}
