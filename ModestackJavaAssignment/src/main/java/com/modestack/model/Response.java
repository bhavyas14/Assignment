package com.modestack.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class Response {

	private int statusCode;
	
	private List<Message> body;
	

	public Response() {
		
	}
	public Response(Response response) {
		
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	  
	  public List<Message> getBody() { 
		  return body;
		  }
	  
	  public void setBody(List<Message> body) { 
		  this.body = body; 
		  }

	
}
