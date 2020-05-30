package com.modestack.dao;

import java.util.List;

import com.modestack.model.Article;

public interface ArticleDao {
	
	public int createArticle(Article article);
	
	
	List<Article> getArticles(int start,int size);
	

}
