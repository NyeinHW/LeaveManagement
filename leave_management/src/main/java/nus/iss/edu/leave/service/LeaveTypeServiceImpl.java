package nus.iss.edu.leave.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.iss.edu.leave.model.LeaveType;
import nus.iss.edu.leave.repo.LeaveTypeRepository;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {
	
	@Autowired
	LeaveTypeRepository ltrepo;
	
	@Override
	public LeaveType findLeaveTypeByName(String name) {	
		return ltrepo.findLeaveTypeByName(name);
	}
	
	@Override
	public ArrayList<LeaveType> findAllLeaveType(){
		
		List<LeaveType> list = ltrepo.findAll();
		ArrayList<LeaveType> leavetypelist = new ArrayList<LeaveType>() ;

		for (Iterator<LeaveType> iterator = list.iterator(); iterator.hasNext();) {
			LeaveType type = (LeaveType) iterator.next();
			leavetypelist.add(type);
		}

		return leavetypelist;
	}
}
