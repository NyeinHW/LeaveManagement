package nus.iss.edu.leave.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
public class Admin {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	@NotEmpty
	@Length(min=8)
	private String password;
	@Email
	private String email;
	private Role role;
	public Admin() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Admin(String name, @NotEmpty @Length(min = 8) String password, @Email String email, Role role) {
		super();
		this.name = name;
		this.password = password;
		this.email = email;
		this.role = role;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", role=" + role
				+ "]";
	}
	

}
