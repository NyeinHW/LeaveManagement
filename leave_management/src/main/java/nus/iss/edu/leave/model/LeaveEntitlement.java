package nus.iss.edu.leave.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
@Entity
@Table(name="leaveentitlement")
public class LeaveEntitlement {
	@Id
	@GeneratedValue
	private int id;
	private int leaveId;
	
	public Mykey mykey;
	
	public Mykey getMykey() {
		return mykey;
	}
	public void setMykey(Mykey mykey) {
		this.mykey = mykey;
	}
	public int getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
		System.out.println(leaveId);
	}
	

	
	public LeaveEntitlement() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public LeaveEntitlement(int id, Mykey mykey, @NotNull double maxnoofdays) {
		super();
		this.id = id;
		this.mykey = mykey;
	}
	public LeaveEntitlement(int id , double maxnoofdays) {
		super();
		this.id = id;
	}
	public LeaveEntitlement(double maxnoofdays) {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "LeaveEntitlement [id=" + id + ", leaveId=" + leaveId + ", mykey=" + mykey + "]";
	}

	
	
	

	
}
