package nus.iss.edu.leave.service;

import java.util.ArrayList;

import nus.iss.edu.leave.model.LeaveType;

public interface LeaveTypeService {
	
	public LeaveType findLeaveTypeByName(String name);
	public ArrayList<LeaveType> findAllLeaveType();
}
