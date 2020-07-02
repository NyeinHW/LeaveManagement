package nus.iss.edu.leave.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
@Entity
public class LeaveEntitlement {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private LeaveType type;
	private Role role;
	private int leave_count;
	@OneToMany(mappedBy="leaveentitlement")
	private List<LeaveBalance> leavebalance;
	
	public LeaveEntitlement() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LeaveEntitlement(LeaveType type, Role role, int leave_count) {
		super();
		this.type = type;
		this.role = role;
		this.leave_count = leave_count;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LeaveType getType() {
		return type;
	}
	public void setType(LeaveType type) {
		this.type = type;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public int getLeave_count() {
		return leave_count;
	}
	public void setLeave_count(int leave_count) {
		this.leave_count = leave_count;
	}
	@Override
	public String toString() {
		return "LeaveEntitlement [type=" + type + ", role=" + role + ", leave_count=" + leave_count + "]";
	}
	
	
}
