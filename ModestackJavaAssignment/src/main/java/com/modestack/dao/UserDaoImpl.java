package com.modestack.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.modestack.model.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao{

	private JdbcTemplate jdbcTemplate;
	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public int registerUser(User user) {
		int count = jdbcTemplate.update(
				"INSERT INTO new_schema1.user_master(user_id,user_name, email, city,password)VALUES(?,?,?,?,?)", 
				new Object[] {
				user.getUserId(), user.getUserName(), user.getEmail(), user.getAddress(),user.getPassword()});
		return count;
	}

	
	public boolean isUserExist(User user) {
		User user1 = null;
		  try {
			  user1 = jdbcTemplate.queryForObject("SELECT * FROM new_schema1.user_master WHERE user_name = ?"
			  		+ "and password = ?",
		     new Object[] { user.getUserName(),user.getPassword() }, new BeanPropertyRowMapper<User>(User.class));
		  } catch (DataAccessException e) {
		   e.printStackTrace();
		   return false;
		  }
		return true;
	}

}
