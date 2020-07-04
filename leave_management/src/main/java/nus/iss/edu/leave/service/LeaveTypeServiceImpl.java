package nus.iss.edu.leave.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.iss.edu.leave.model.LeaveEntitlement;
import nus.iss.edu.leave.model.LeaveType;
import nus.iss.edu.leave.repo.LeaveEntitlementRepository;
import nus.iss.edu.leave.repo.LeaveTypeRepository;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {
	@Autowired
	LeaveTypeRepository ltrepo;
	
	@Autowired 
	LeaveEntitlementRepository lerepo;
	
	@Override
	public LeaveType findLeaveTypeByName(String name) {	
		return ltrepo.findLeaveTypeByName(name);
	}
	
	@Override
	public ArrayList<LeaveType> findAllLeaveType(){
		
		List<LeaveType> list = ltrepo.findAll();
		
		ArrayList<LeaveType> leavetypelist = new ArrayList<LeaveType>() ;
		
		for (Iterator<LeaveType> iterator = list.iterator(); iterator.hasNext();) {
			System.out.println("iterating through");
			LeaveType type = (LeaveType) iterator.next();
			leavetypelist.add(type);
		}
		

		return leavetypelist;
	}
	
	@Override 
	public void createOrUpdateLeaveType(LeaveType newleavetype) {
		System.out.println("reach create controller");
		
		Optional<LeaveType> lt = ltrepo.findById(newleavetype.getId());
		if (!lt.isPresent()) {
			LeaveType newEntity = new LeaveType(); 
			newEntity.setType(newleavetype.getType());
			
			if (!newleavetype.getMaxNoOfDays().isEmpty()) {
			newEntity.setMaxNoOfDays(newleavetype.getMaxNoOfDays());
			ltrepo.save(newEntity);	
			System.out.println("reached assign max days");}
			else {
			newEntity.setMaxNoOfDays("-");	
			ltrepo.save(newEntity);}
		}
		else if (lt.isPresent()){
			LeaveType newEntity = lt.get(); 
			newEntity.setType(newleavetype.getType());
			
			if (newleavetype.getMaxNoOfDays().isEmpty()) newEntity.setMaxNoOfDays("-");
			else newEntity.setMaxNoOfDays(newleavetype.getMaxNoOfDays());
			ltrepo.save(newEntity);
		}
		}
		
		
		
	
	
	@Override 
	public void deleteLeaveType(Integer id) {
		LeaveType lt = ltrepo.findLeaveTypeById(id);
		List<LeaveEntitlement> entitlements = lerepo.findByLeavetype(lt);
		for (LeaveEntitlement le : entitlements) {
			le.setType(null); 
		}
		ltrepo.delete(lt);
	}
	
}
