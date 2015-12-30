package controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import beans.Product;
import model.ProductDao;

public class ProductController {

	private ProductDao productDao = new ProductDao();
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	public void addNewProduct() throws Exception {
		System.out.println("================");
		System.out.print("Enter product name: ");
		String name = input.readLine();
		System.out.print("Enter product description: ");
		String description = input.readLine();
		System.out.print("Enter product price: ");
		double price = Double.parseDouble(input.readLine());

		productDao.saveProduct(name, description, price);
	}

	public void removeExistingProduct() throws Exception {
		System.out.println("================");
		System.out.print("Enter product id: ");
		int id = Integer.parseInt(input.readLine());

		productDao.deleteProduct(id);
	}

	public void listAllProducts() throws SQLException {
		System.out.println("================");
		System.out.println("Products:");

		List<Product> products = productDao.getProducts();
		for (Product product : products) {
			printProduct(product);
		}
	}

	private void printProduct(Product product) {
		System.out.println("Product --> Id: " + product.getId() + ", Name: " + product.getName() + ", Description: "
				+ product.getDescription() + ", Price: " + product.getPrice());

	}
}
