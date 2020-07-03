package nus.iss.edu.leave.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Embeddable
public class LeaveBalanceIdentity implements Serializable{
		
		private int employeeid;
		@NotNull
		private int leavetypeid;
		public LeaveBalanceIdentity() {
			super();
			// TODO Auto-generated constructor stub
		}
		public LeaveBalanceIdentity( int employeeid, @NotNull int leavetypeid) {
			super();
			this.employeeid = employeeid;
			this.leavetypeid = leavetypeid;
		}

		public int getEmployeeid() {
			return employeeid;
		}
		public void setEmployeeid(int employeeid) {
			this.employeeid = employeeid;
		}
		public int getLeavetypeid() {
			return leavetypeid;
		}
		public void setLeavetypeid(int leavetypeid) {
			this.leavetypeid = leavetypeid;
		}
		@Override
		public String toString() {
			return "LeaveBalanceIdentity [employeeid=" + employeeid + ", leavetypeid=" + leavetypeid + "]";
		}
		

}
