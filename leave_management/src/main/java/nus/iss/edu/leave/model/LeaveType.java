package nus.iss.edu.leave.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="leavetype")
public class LeaveType {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@NotEmpty
	private String type; 
	
	private String MaxNoOfDays;
	// Not Employee Leave entitlement, it's the maximum 
	//number of days in company policy e.g. Medical Leave cannot 
	//exceed 60 days 
	
	@OneToMany(mappedBy="leavetype")
	private List<LeaveEntitlement> leaveentitlement;
	
	public LeaveType() {
		super();
	}
	
	public LeaveType(String type) {
		super();
		this.type = type;
	}
	
	public LeaveType(String type, String MaxDays) {
		super();
		this.type = type;
		this.MaxNoOfDays = MaxDays;
	}

	public int getId() {
		return id;
	}

	public List<LeaveEntitlement> getLeaveentitlement() {
		return leaveentitlement;
	}

	public void setLeaveentitlement(List<LeaveEntitlement> leaveentitlement) {
		this.leaveentitlement = leaveentitlement;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMaxNoOfDays() {
		return MaxNoOfDays;
	}

	public void setMaxNoOfDays(String maxNoOfDays) {
		MaxNoOfDays = maxNoOfDays;
	}	
}
