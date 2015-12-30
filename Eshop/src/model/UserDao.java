package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.User;
import eshop.Eshop;

public class UserDao {

	public void saveUser(String login, String password, String name, int admin) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Eshop.DB_URL, Eshop.USER, Eshop.PASSWORD);
			stmt = conn.prepareStatement("INSERT INTO user (login, password, name, admin) VALUES (?, ?, ?, ?)");
			stmt.setString(1, login);
			stmt.setString(2, password);
			stmt.setString(3, name);
			stmt.setInt(4, admin);
			stmt.executeUpdate();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public List<User> getUsers() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(Eshop.DB_URL, Eshop.USER, Eshop.PASSWORD);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM user order by name ASC");

			List<User> users = new ArrayList<User>();

			while (rs.next()) {
				String login = rs.getString("login");
				String password = rs.getString("password");
				String name = rs.getString("name");
				int admin = rs.getInt("admin");

				User user = new User();
				user.setLogin(login);
				user.setPassword(password);
				user.setName(name);
				user.setAdmin(admin);

				users.add(user);
			}
			return users;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public User getUser(String login) throws SQLException {

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(Eshop.DB_URL, Eshop.USER, Eshop.PASSWORD);
			stmt = conn.prepareStatement("SELECT login, password, name, admin FROM user WHERE login = ?");
			stmt.setString(1, login);
			rs = stmt.executeQuery();

			if (rs.next()) {
				User user = new User();
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setAdmin(rs.getInt("admin"));
				return user;
			}
			return null;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
}
