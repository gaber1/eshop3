package eshop;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import beans.User;
import controllers.OrderController;
import controllers.ProductController;
import controllers.UserController;

public class Eshop {

	public static final String DB_URL = "jdbc:mysql://localhost/eshop";
	public static final String USER = "root";
	public static final String PASSWORD = "";

	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

	private ProductController productController = new ProductController();
	private OrderController orderController = new OrderController();
	private UserController userController = new UserController();

	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws Exception {
		Class.forName(JDBC_DRIVER);

		Eshop eshop = new Eshop();
		eshop.mainMenu();
	}

	private void mainMenu() throws Exception {

		System.out.println("Welcome to Eshop");

		User user = userController.getUser();

		if (user == null) {
			System.exit(0);
		}
		if (!userController.checkUserPassword(user)) {
			System.exit(0);
		}
		if (user.isAdmin() == 1) {
			printAdminMenu(user);
		} else {
			printCustomerMenu(user);
		}

	}

	private void printCustomerMenu(User user) throws Exception {

		while (true) {
			System.out.println("================");
			System.out.println("1. List all products");
			System.out.println("2. Order a product");
			System.out.println("3. Exit");
			System.out.print("Enter your choice (1-3): ");
			int choice = 0;

			try {
				choice = Integer.parseInt(input.readLine());
			} catch (NumberFormatException e) {
				System.out.println("You have to enter a number between (1-3)!");

			}

			switch (choice) {
			case 1:
				productController.listAllProducts();
				break;
			case 2:
				orderController.orderProduct(user.getLogin());
				break;
			case 3:
				System.exit(0);
				break;
			default:
				System.out.println("Your choice is not in range (1-3), try again.");
				break;
			}
		}
	}

	private void printAdminMenu(User user) throws Exception {

		while (true) {
			System.out.println("================");
			System.out.println("1. Add a new product");
			System.out.println("2. Remove existing product");
			System.out.println("3. List all products");
			System.out.println("4. Order a product");
			System.out.println("5. List all orders");
			System.out.println("6. Add new user");
			System.out.println("7. List all users");
			System.out.println("8. Exit");
			System.out.print("Enter your choice (1-8): ");
			int choice = 0;

			try {
				choice = Integer.parseInt(input.readLine());
			} catch (NumberFormatException e) {
				System.out.println("You have to enter a number between (1-8)!");

			}

			switch (choice) {
			case 1:
				productController.addNewProduct();
				break;
			case 2:
				productController.removeExistingProduct();
				break;
			case 3:
				productController.listAllProducts();
				break;
			case 4:
				orderController.orderProduct(user.getLogin());
				break;
			case 5:
				orderController.listAllOrders();
				break;
			case 6:
				userController.addNewUser();
				break;
			case 7:
				userController.listAllUsers();
				break;
			case 8:
				System.exit(0);
				break;
			default:
				System.out.println("Your choice is not in range (1-8), try again.");
				break;
			}

		}

	}
}
