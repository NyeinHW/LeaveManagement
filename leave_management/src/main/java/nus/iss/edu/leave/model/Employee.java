package nus.iss.edu.leave.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String name;
	@NotEmpty
	private String username;
	@NotEmpty
	@Length(min=8)
	private String password;
	private int contact_no;
	@Email
	private String email;
	private String address;
	private Role role;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date doh;
	private int manager_id;
	@OneToMany(mappedBy="employee")
	private List<LeaveApplication> leaveapplication;
	@OneToMany(mappedBy="employee")
	private List<LeaveBalance> leavebalance;
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(String name, @NotEmpty String username, @NotEmpty @Length(min = 8) String password, int contact_no,
			@Email String email, String address, Role role, Date doh, int manager_id) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.contact_no = contact_no;
		this.email = email;
		this.address = address;
		this.role = role;
		this.doh = doh;
		this.manager_id = manager_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public int getManager_id() {
		return manager_id;
	}
	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}
	public List<LeaveApplication> getLeaveapplication() {
		return leaveapplication;
	}
	public void setLeaveapplication(List<LeaveApplication> leaveapplication) {
		this.leaveapplication = leaveapplication;
	}
	public List<LeaveBalance> getLeavebalance() {
		return leavebalance;
	}
	public void setLeavebalance(List<LeaveBalance> leavebalance) {
		this.leavebalance = leavebalance;
	}
	public int getId() {
		return id;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password
				+ ", contact_no=" + contact_no + ", email=" + email + ", address=" + address + ", role=" + role
				+ ", doh=" + doh + ", manager_id=" + manager_id + ", leaveapplication=" + leaveapplication
				+ ", leavebalance=" + leavebalance + "]";
	}
	
}
