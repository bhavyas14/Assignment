package com.modestack.model;

import java.util.List;

public class ArticleResponse {
	
	private int statusCode;
	
	private Body body;



	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}



}
