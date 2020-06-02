package com.modestack.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.modestack.dao.ArticleDao;
import com.modestack.model.Article;

@Service("articleService")
@Transactional
public class ArticleServiceImpl implements ArticleService{

	@Autowired
	ArticleDao articleDao;
	
	
	public int createArticle(Article article) {
		int count = articleDao.createArticle(article);
		return count;
	}
	


	/*
	 * @Override public List<Article> getArticles(int start, int size) {
	 * List<Article> articles = articleDao.getArticles(start,size); return articles;
	 * }
	 */



	@Override
	public List<Article> getPaginatedArticles(int page, int size) {
		List<Article> articles = articleDao.getPaginatedArticles(page, size);
		return articles;
	}

}
