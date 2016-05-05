package com.dao.implement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.dao.UserDao;
import com.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void add(User user) {
		String sqlStr = "insert into user(name,password) values(?,?)";
		Object[] params = new Object[] { user.getName(), user.getPassword() };
		jdbcTemplate.update(sqlStr, params);
	}

	public User findUserByName(String name) {
		String sqlStr = "select id,name,password from user where name=?";
		final User user = new User();
		jdbcTemplate.query(sqlStr, new Object[] { name }, new RowCallbackHandler() {

			public void processRow(ResultSet rs) throws SQLException {
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
			}
		});
		return user;
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		String sqlStr = "select id,name,password from user";
		final List<User> lUsers = new ArrayList<>();
		jdbcTemplate.query(sqlStr, new RowCallbackHandler() {

			public void processRow(ResultSet rs) throws SQLException {
				do {
					User user = new User();
					user.setId(rs.getInt("id"));
					user.setName(rs.getString("name"));
					user.setPassword(rs.getString("password"));
					lUsers.add(user);
				} while (rs.next());
			}
		});
		return lUsers;

	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		String sqlStr = "delete from user where id=?";
		jdbcTemplate.update(sqlStr,new Object[]{id});
	}

}