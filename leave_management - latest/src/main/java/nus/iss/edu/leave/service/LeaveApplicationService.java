package nus.iss.edu.leave.service;

import java.util.ArrayList;
import java.util.Date;

import nus.iss.edu.leave.model.LeaveApplication;
import nus.iss.edu.leave.model.LeaveBalance;

public interface LeaveApplicationService {
	
	public void addLeaveApplication(LeaveApplication leaveapp);
	public boolean leaveValidation(LeaveApplication leaveapp);
	public ArrayList<LeaveApplication> findAllLeaveApplications();
	public LeaveApplication findLeaveApplicationById(Integer id);
	public void deleteLeaveApplication(LeaveApplication leaveApplication);
	public void cancelLeaveApplication(LeaveApplication leaveApplication);
	public ArrayList<LeaveApplication> findAllLeaveApplicationByEmployeeId(Integer id);
	public boolean isUnique(int id,Date startDate,Date endDate,String type);
	public void approvedLeaveApplication(LeaveApplication leaveApplication);
	public void rejectedLeaveApplication(LeaveApplication leaveApplication);

}
