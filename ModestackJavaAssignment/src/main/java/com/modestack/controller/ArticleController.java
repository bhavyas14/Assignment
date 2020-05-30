package com.modestack.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.modestack.model.Article;
import com.modestack.model.ArticleResponse;
import com.modestack.model.Body;
import com.modestack.model.Message;
import com.modestack.model.Response;
import com.modestack.service.ArticleService;

@RestController
@RequestMapping(value = "/")
public class ArticleController {
	public static final int POST_CODE = 201;
	public static final int GET_CODE = 200;
	Message message =  null;
	Response response = null;
	List<Message> list = null;

	@Autowired
	ArticleService articleService;

	@RequestMapping(value = "articles", method = RequestMethod.POST)
	public ResponseEntity<Response> createArticle(@RequestBody Article article) {
		message = new Message();
		list = new ArrayList<Message>();
		response = new Response();

		int count = articleService.createArticle(article);
		if (!(count > 0)) {
			System.out.println("No user registered");
			response.setStatusCode(409);
			message.setMessage("No article created");
			list.add(message);
			response.setBody(list);
			return new ResponseEntity<Response>(response,HttpStatus.CONFLICT);
		}

		response.setStatusCode(POST_CODE);
		message.setMessage("New article created");
		list.add(message);
		response.setBody(list);
		return new ResponseEntity<Response>(response, HttpStatus.CREATED);
	}


  	
  	@RequestMapping(value = "articles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE) 
	public ResponseEntity<ArticleResponse> getArticles(@RequestParam("start") int start,
			@RequestParam("size") int size) { 
	ArticleResponse articleResponse = new ArticleResponse();
	response = new Response();
	Body body = new Body();
	message = new Message();
	list = new ArrayList<Message>();
	List<Article> data = null;

	if(start >=0 && size >=0) {
		System.out.println("Inside if");
	data = articleService.getArticles(start,size); 
	}
	if(data == null) {
		response.setStatusCode(409);
		message.setMessage("No article created");
		list.add(message);
		response.setBody(list);
		//return new ResponseEntity<Response>(response,HttpStatus.CONFLICT);	
	}
	body.setData(data);
	articleResponse.setStatusCode(GET_CODE); 
	articleResponse.setBody(body);


	return new ResponseEntity<ArticleResponse>(articleResponse, HttpStatus.OK); }






}
