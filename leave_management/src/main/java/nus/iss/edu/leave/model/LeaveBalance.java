package nus.iss.edu.leave.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class LeaveBalance {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToOne
	private Employee employee;
	@ManyToOne
	private LeaveEntitlement leaveentitlement;
	@NotEmpty
	private int balance;
	
	public LeaveBalance() {
		super();
	}
	public LeaveBalance(int id, Employee employee, LeaveEntitlement leaveentitlement, @NotEmpty int balance) {
		super();
		this.id = id;
		this.employee = employee;
		this.leaveentitlement = leaveentitlement;
		this.balance = balance;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public LeaveEntitlement getLeaveentitlement() {
		return leaveentitlement;
	}
	public void setLeaveentitlement(LeaveEntitlement leaveentitlement) {
		this.leaveentitlement = leaveentitlement;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
}
