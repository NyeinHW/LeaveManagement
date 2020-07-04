package nus.iss.edu.leave.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import nus.iss.edu.leave.model.Employee;
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
		System.out.print("Retrieving leave entitlement");
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
	
	@Override
	public List<LeaveEntitlement> findAll(){
		System.out.print("Reached LE service layer");		
		return leRepo.findAll();
	}
	
	@Override 
	public void UpdateLE(LeaveEntitlement newle) {
		System.out.println("UpdateLE reached");
		Optional<LeaveEntitlement> le = leRepo.findById(newle.getId());
		if (le.isPresent()) {
			LeaveEntitlement newEntity = le.get(); 
			newEntity.setType(newle.getType());
			newEntity.setLeave_count(newle.getLeave_count());
			newEntity.setRole(newle.getRole());
			leRepo.save(newEntity);
		}
	}

}
