package nus.iss.edu.leave.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="leaveentitlement")
public class LeaveEntitlement {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToOne
	private LeaveType leavetype;

	private Role role;
	private int leave_count;
	
	@OneToMany(mappedBy="leaveentitlement")
	private List<LeaveBalance> leavebalance;
	
	@OneToMany(mappedBy="leaveentitlement")
	private List<LeaveApplication> leaveapplication;
	
	public LeaveEntitlement() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public LeaveEntitlement(LeaveType type, Role role, int leave_count) {
		super();
		this.leavetype = type;
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
		return leavetype;
	}
	public void setType(LeaveType type) {
		this.leavetype = type;
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
		return "LeaveEntitlement [type=" + leavetype + ", role=" + role + ", leave_count=" + leave_count + "]";
	}
	
	
}
