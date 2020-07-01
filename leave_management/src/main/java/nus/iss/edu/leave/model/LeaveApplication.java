package nus.iss.edu.leave.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name ="leaveapplication")
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
	private String work_dissemination;
	@ManyToOne
	private Employee employee;
	@OneToOne
	private LeaveEntitlement leaveentitlement;
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
	
	
	
	public LeaveApplication(String reason, Date start_date, Date end_date, Status status, String manager_cmt,
			String work_dissemination, Employee employee, LeaveEntitlement leaveentitlement) {
		super();
		this.reason = reason;
		this.start_date = start_date;
		this.end_date = end_date;
		this.status = status;
		this.manager_cmt = manager_cmt;
		this.work_dissemination = work_dissemination;
		this.employee = employee;
		this.leaveentitlement = leaveentitlement;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getManager_cmt() {
		return manager_cmt;
	}
	public void setManager_cmt(String manager_cmt) {
		this.manager_cmt = manager_cmt;
	}
	public String getWork_dissemination() {
		return work_dissemination;
	}
	public void setWork_dissemination(String work_dissemination) {
		this.work_dissemination = work_dissemination;
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
	@Override
	public String toString() {
		return "LeaveApplication [id=" + id + ", reason=" + reason + ", start_date=" + start_date + ", end_date="
				+ end_date + ", status=" + status + ", manager_cmt=" + manager_cmt + "]";
	}
	

}
