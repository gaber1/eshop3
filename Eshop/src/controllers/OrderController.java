package controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Order;
import beans.Product;
import model.OrderDao;

public class OrderController {

	private OrderDao orderDao = new OrderDao();
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	public void orderProduct(String userLogin) throws Exception {
		System.out.println("================");
		List<Integer> productIds = new ArrayList<Integer>();
		while (true) {
			System.out.print("Enter product id (or 'f' for finished): ");
			String stringId = input.readLine();
			if (stringId.equals("f")) {
				break;
			} else {
				int id = Integer.parseInt(stringId);
				productIds.add(id);
			}
		}

		if (productIds.isEmpty()) {
			System.out.println("Error: no product id entered.");
			return;
		}

		orderDao.saveOrder(productIds, userLogin);
	}

	public void listAllOrders() throws SQLException {
		System.out.println("================");
		System.out.println("Orders:");

		List<Order> orders = orderDao.getOrders();

		for (Order order : orders) {
			printOrder(order); 
		}
	}

	private void printOrder(Order order) {
		System.out.println("Order --> Id: " + order.getId());

		List<Product> products = order.getProducts();
		for (Product product : products) {
			printProduct(product);
		}
	}

	private void printProduct(Product product) {
		System.out.println("Product --> Id: " + product.getId() + ", Name: " + product.getName() + ", Description: "
				+ product.getDescription() + ", Price: " + product.getPrice());
	}
}
