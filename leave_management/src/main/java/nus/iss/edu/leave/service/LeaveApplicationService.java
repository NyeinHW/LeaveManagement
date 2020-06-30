package nus.iss.edu.leave.service;

import nus.iss.edu.leave.model.LeaveApplication;
import nus.iss.edu.leave.model.LeaveBalance;

public interface LeaveApplicationService {
	
	public void addLeaveApplication(LeaveApplication leaveapp);
	public boolean leaveValidation(LeaveApplication leaveapp);
}
