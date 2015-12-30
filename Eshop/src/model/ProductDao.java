package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Product;
import eshop.Eshop;

public class ProductDao {

	public void saveProduct(String name, String description, double price) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Eshop.DB_URL, Eshop.USER, Eshop.PASSWORD);
			stmt = conn.prepareStatement("INSERT INTO product (name, description, price) VALUES (?, ?, ?)");
			stmt.setString(1, name);
			stmt.setString(2, description);
			stmt.setDouble(3, price);
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

	public void deleteProduct(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Eshop.DB_URL, Eshop.USER, Eshop.PASSWORD);
			stmt = conn.prepareStatement("delete from product where product_id = ?");
			stmt.setInt(1, id);
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

	public List<Product> getProducts() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(Eshop.DB_URL, Eshop.USER, Eshop.PASSWORD);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM product");

			List<Product> products = new ArrayList<Product>();

			while (rs.next()) {
				int id = rs.getInt("product_id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				double price = rs.getDouble("price");

				Product product = new Product();
				product.setId(id);
				product.setName(name);
				product.setDescription(description);
				product.setPrice(price);

				products.add(product);
			}
			return products;
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
