package nus.iss.edu.leave.service;

import java.util.ArrayList;

import nus.iss.edu.leave.model.LeaveEntitlement;
import nus.iss.edu.leave.model.LeaveType;
import nus.iss.edu.leave.model.Role;

public interface LeaveEntitlementService {
	
	 public boolean saveLeaveEntitlement(LeaveEntitlement leaveEntitlement);
	 public LeaveEntitlement findLeaveEntitlementById(Integer id);
	 public void deleteLeaveEntitlement(LeaveEntitlement leaveEntitlement);
	 //public LeaveEntitlement findLeaveEntitlementByType(LeaveType type,Role role);

}
