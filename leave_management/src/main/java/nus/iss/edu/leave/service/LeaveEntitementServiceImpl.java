package nus.iss.edu.leave.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nus.iss.edu.leave.model.LeaveEntitlement;
import nus.iss.edu.leave.model.Role;
import nus.iss.edu.leave.repo.LeaveEntitlementRepository;

@Service
public class LeaveEntitementServiceImpl implements LeaveEntitlementService {
	
	@Autowired
	LeaveEntitlementRepository leRepo;
	
	@Transactional
	public boolean saveLeaveEntitlement(LeaveEntitlement leaveEntitlement) {
		if(leRepo.save(leaveEntitlement)!=null) return true; else return false;
	}

	@Override
	public LeaveEntitlement findLeaveEntitlementById(Integer id) {
		
		return leRepo.findById(id).get();
	}

	@Override
	public LeaveEntitlement findLeaveEntitlementByType(String type,Role role) {
		
		return null;
	}

	@Transactional
	public void deleteLeaveEntitlement(LeaveEntitlement LeaveEntitlement) {
		leRepo.delete(LeaveEntitlement);
		
	}

}
