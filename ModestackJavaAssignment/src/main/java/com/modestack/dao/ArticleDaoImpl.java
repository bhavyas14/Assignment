package com.modestack.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.modestack.model.Article;

@Repository("articleDao")
public class ArticleDaoImpl implements ArticleDao {
	
	private JdbcTemplate jdbcTemplate;
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int createArticle(Article article) {
		int count = jdbcTemplate.update(
				"INSERT INTO new_schema1.article(title,body, author)VALUES(?,?,?)", 
				new Object[] {
				article.getTitle(), article.getBody(), article.getAuthor()});
		return count;
	}

	/*
	 * @Override public List<Article> getArticles(int start,int size) {
	 * List<Article> articles = null ;
	 * 
	 * try { articles = jdbcTemplate.query("SELECT * FROM new_schema1.article",new
	 * BeanPropertyRowMapper<Article>(Article.class)); } catch (DataAccessException
	 * e) { e.printStackTrace(); return articles; }
	 * 
	 * if(start + size > articles.size()) { return new ArrayList<Article>(); }
	 * System.out.println("Dao impl"); return articles.subList(start, size); //
	 * return articles; }
	 */

	@Override
	public List<Article> getPaginatedArticles(int page, int size) {
		String sql="SELECT * FROM new_schema1.article limit "+(page-1)+","+size;    
	    return jdbcTemplate.query(sql,new RowMapper<Article>(){

			@Override
			public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
				Article obj = new Article();
				obj.setTitle(rs.getString(1));
				obj.setBody(rs.getString(2));
				obj.setAuthor(rs.getString(3));
				return obj;
			}});   
	}


}
