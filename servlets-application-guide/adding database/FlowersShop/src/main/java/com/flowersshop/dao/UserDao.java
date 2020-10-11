package com.flowersshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.flowersshop.config.DatabaseUtils;
import com.flowersshop.model.User;

public class UserDao {
	
	public UserDao() {

	}
	
	public void insertUser(User user) throws SQLException, ClassNotFoundException {
		insertUser(DatabaseUtils.getMySQLConnection(), user);
	}
	
	public User findUser(String username, String password) throws SQLException, ClassNotFoundException {
		return findUser(DatabaseUtils.getMySQLConnection(), username, password);
	}
	
	private boolean insertUser(Connection conn, User user) throws SQLException {
		 
        String sql = "insert into user (first_name, last_name, username, email, password, address, contact) "
        		+ "values (?, ?, ?, ?, ?, ?, ?)";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, user.getFirstName());
        pstm.setString(2, user.getLastName());
        pstm.setString(3, user.getUsername());
        pstm.setString(4, user.getEmail());
        pstm.setString(5, user.getPassword());
        pstm.setString(6, user.getAddress());
        pstm.setString(7, user.getContact());

        return pstm.execute();
    }
	
	public User findUser(Connection conn, String username, String password) throws SQLException {
 
        String sql = "select * from user where username = ? and password= ?";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, username);
        pstm.setString(2, password);
        ResultSet rs = pstm.executeQuery();
 
        if (rs.next()) {
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("first_name");
            String email = rs.getString("email");
            String address = rs.getString("address");
            String contact = rs.getString("contact");
            User user = new User(firstName, lastName, username, 
					email, password, address, contact);
            return user;
        }
        return null;
    }
	
}
