package nus.iss.edu.leave.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@NotEmpty
	private String name;
	@NotEmpty
	private String password;
	private int contact_no;
	@Email
	private String email;
	private String address;
	private Role role;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date doh;
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(@NotEmpty String name, @NotEmpty String password, int contact_no, @Email String email,
			String address, Role role, Date doh) {
		super();
		this.name = name;
		this.password = password;
		this.contact_no = contact_no;
		this.email = email;
		this.address = address;
		this.role = role;
		this.doh = doh;
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
	public int getContact_no() {
		return contact_no;
	}
	public void setContact_no(int contact_no) {
		this.contact_no = contact_no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Date getDoh() {
		return doh;
	}
	public void setDoh(Date doh) {
		this.doh = doh;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", password=" + password + ", contact_no=" + contact_no
				+ ", email=" + email + ", address=" + address + ", role=" + role + ", doh=" + doh + "]";
	}
	
	
	

}
