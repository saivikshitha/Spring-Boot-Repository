package vikshii.namedParameterJDBC.model;

import java.util.Date;

public class User {
	private int id;
	private String name;
	private String address;
	private String email;
	private Date todayDate;
	private String village;

	public String getVillage() {
		return village;
	}

	public void setVillage(String string) {
		this.village = string;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getTodayDate() {
		return todayDate;
	}

	public void setTodayDate(Date todayDate) {
		this.todayDate = todayDate;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", address=" + address + ", email=" + email + "]";
	}

	public User(int id, String name, String address, String email, String village, Date todayDate) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.email = email;
		this.village = village;
		this.todayDate = todayDate;
	}

	public User(String name, String address, String email) {
		super();

		this.name = name;
		this.address = address;
		this.email = email;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

}
