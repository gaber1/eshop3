package beans;

public class User {
	private String login;
	private String password;
	private String name;
	private int admin;

	public User(String login, String password, String name, int admin) {
		this.login = login;
		this.password = password;
		this.name = name;
		this.admin = admin;
	}
	
	public User() {
		
	}

	public int isAdmin() {
		return admin;
	}

	public void setAdmin(int admin) {
		this.admin = admin;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
