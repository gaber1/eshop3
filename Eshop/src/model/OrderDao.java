package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Order;
import beans.Product;
import eshop.Eshop;

public class OrderDao {

	public void saveOrder(List<Integer> productIds, String userLogin) throws SQLException {
		int nextOrderId = getNextOrderId();
		insertOrder(nextOrderId, userLogin);
		for (Integer productId : productIds) {
			insertOrderProduct(nextOrderId, productId);
		}
	}

	private int getNextOrderId() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(Eshop.DB_URL, Eshop.USER, Eshop.PASSWORD);
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT MAX(order_id) as max_order_id FROM `order`");

			if (rs.next()) {
				int maxOrderId = rs.getInt("max_order_id");
				int nextOrderId = ++maxOrderId;
				return nextOrderId;
			} else {
				return 1;
			}
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

	private void insertOrder(int orderId, String userLogin) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Eshop.DB_URL, Eshop.USER, Eshop.PASSWORD);
			stmt = conn.prepareStatement("INSERT INTO `order` (order_id, login_name) VALUES (?, ?)");
			stmt.setInt(1, orderId);
			stmt.setString(2, userLogin);
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

	private void insertOrderProduct(int orderId, int productId) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(Eshop.DB_URL, Eshop.USER, Eshop.PASSWORD);
			stmt = conn.prepareStatement("INSERT INTO order_product (order_id, product_id) VALUES (?, ?)");
			stmt.setInt(1, orderId);
			stmt.setInt(2, productId);
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

	public List<Order> getOrders() throws SQLException {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(Eshop.DB_URL, Eshop.USER, Eshop.PASSWORD);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"SELECT `order`.order_id, product.product_id, product.name, product.description, product.price FROM `order` JOIN order_product ON `order`.order_id = order_product.order_id JOIN product ON order_product.product_id = product.product_id");

			List<Order> orders = new ArrayList<Order>();

			while (rs.next()) {
				int orderId = rs.getInt("order_id");
				int productId = rs.getInt("product_id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				double price = rs.getDouble("price");

				Product product = new Product(productId, name, description, price);

				Order actualOrder = null;
				for (Order order : orders) {
					if (orderId == order.getId()) {
						actualOrder = order;
					}
				}
				if (actualOrder == null) {
					List<Product> products = new ArrayList<Product>();
					products.add(product);

					actualOrder = new Order(orderId, products);

					orders.add(actualOrder);
				} else {
					actualOrder.getProducts().add(product);
				}
			}
			return orders;
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
