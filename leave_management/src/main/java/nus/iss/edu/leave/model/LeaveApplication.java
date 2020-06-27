package nus.iss.edu.leave.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class LeaveApplication {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String reason;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date start_date; 
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd-MM-yyyy")
	private Date end_date;
	private Status status;
	private String manager_cmt;
	public LeaveApplication() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LeaveApplication(String reason, Date start_date, Date end_date, Status status, String manager_cmt) {
		super();
		this.reason = reason;
		this.start_date = start_date;
		this.end_date = end_date;
		this.status = status;
		this.manager_cmt = manager_cmt;
	}
	@Override
	public String toString() {
		return "LeaveApplication [id=" + id + ", reason=" + reason + ", start_date=" + start_date + ", end_date="
				+ end_date + ", status=" + status + ", manager_cmt=" + manager_cmt + "]";
	}
	
	

}
