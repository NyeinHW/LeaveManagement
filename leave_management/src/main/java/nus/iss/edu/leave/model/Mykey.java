package nus.iss.edu.leave.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class Mykey implements Serializable {
		@Column(name = "maxnoofdays", nullable = false)
		private double maxnoofdays;

		public double getMaxnoofdays() {
			return maxnoofdays;
		}


		public void setMaxnoofdays(double maxnoofdays) {
			this.maxnoofdays = maxnoofdays;
		}


		public Role getRole() {
		return role;
		}


		public void setRole(Role role) {
			this.role = role;
		}


		public LeaveType getLeavetype() {
			return leavetype;
		}


		public void setLeavetype(LeaveType leavetype) {
			this.leavetype = leavetype;
		}


			@Column(name = "role", nullable = false)
		    private Role role;
			

			@ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH})
			@JoinColumn(name="leaveId", insertable=false, updatable=false)
			private LeaveType leavetype;

			public void setLeavetype(Object leavetype2) {
				// TODO Auto-generated method stub
				
			}



}
