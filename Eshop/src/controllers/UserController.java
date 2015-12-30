package controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import beans.User;
import model.UserDao;

public class UserController {

	private UserDao userDao = new UserDao();
	private BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

	public void addNewUser() throws Exception {
		System.out.println("================");
		System.out.print("Enter your login: ");
		String login = input.readLine();
		System.out.print("Enter password: ");
		String password = input.readLine();
		System.out.print("Enter your name: ");
		String name = input.readLine();
		System.out.print("Admin (yes=1, no=0): ");
		int admin = Integer.parseInt(input.readLine());

		userDao.saveUser(login, password, name, admin);
	}

	public void listAllUsers() throws SQLException {
		System.out.println("================");
		System.out.println("Users:");

		List<User> users = userDao.getUsers();
		for (User user : users) {
			printUser(user);
		}
	}

	private void printUser(User user) {
		System.out.println("User --> Name: " + user.getName() + ", Login: " + user.getLogin());
	}

	public User getUser() throws Exception {
		System.out.println("================");
		System.out.print("Enter your login: ");
		String login = input.readLine();

		return userDao.getUser(login);
	}

	public boolean checkUserPassword(User user) throws Exception {
		System.out.println("================");
		System.out.print("Enter your password: ");
		String password = input.readLine();

		return user.getPassword().equals(password);
	}
}
