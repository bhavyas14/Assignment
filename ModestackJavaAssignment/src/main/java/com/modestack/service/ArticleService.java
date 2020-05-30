package com.modestack.service;

import java.util.List;

import com.modestack.model.Article;

public interface ArticleService {
	
	public int createArticle(Article article);
	
	List<Article> getArticles(int start,int size);

}
