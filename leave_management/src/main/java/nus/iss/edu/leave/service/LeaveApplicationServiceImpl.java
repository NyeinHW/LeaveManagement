package nus.iss.edu.leave.service;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import nus.iss.edu.leave.model.LeaveApplication;
import nus.iss.edu.leave.model.LeaveBalance;
import nus.iss.edu.leave.model.LeaveEntitlement;
import nus.iss.edu.leave.model.LeaveType;
import nus.iss.edu.leave.model.Role;
import nus.iss.edu.leave.model.Status;
import nus.iss.edu.leave.repo.LeaveApplicationRepository;
import nus.iss.edu.leave.repo.LeaveBalanceRepository;
import nus.iss.edu.leave.repo.LeaveEntitlementRepository;
import nus.iss.edu.leave.validator.LeaveApplicationValidator;

@Service
public class LeaveApplicationServiceImpl implements LeaveApplicationService {
	
	@Autowired
	LeaveApplicationRepository larepo;

	@Autowired
	LeaveBalanceRepository lbrepo;
	
	@Autowired
	LeaveEntitlementRepository lerepo;
	
	@Override
	public boolean leaveValidation(LeaveApplication leaveapp) {
		
		/*
		 * LeaveEntitlement leaveEntitlementResult = new LeaveEntitlement();
		 * 
		 * List<LeaveEntitlement> leaveEntitlement = lerepo.findAll(); for
		 * (Iterator<LeaveEntitlement> iterator = leaveEntitlement.iterator();
		 * iterator.hasNext();) { LeaveEntitlement leaveentitle = (LeaveEntitlement)
		 * iterator.next(); System.out.println(leaveentitle); if(leaveentitle.getRole()
		 * == leaveapp.getEmployee().getRole() &&
		 * leaveentitle.getType()==leaveapp.getLeaveentitlement().getType()) {
		 * leaveEntitlementResult = leaveentitle; } } LeaveBalance leavebal =
		 * lbrepo.findLeaveBalance(leaveapp.getEmployee().getId(),
		 * leaveEntitlementResult.getId());
		 * 
		 * int leavebalance = leavebal.getBalance(); 
		 */
		System.out.println(leaveapp);
		
		Date startDate = leaveapp.getStart_date();
		Date endDate = leaveapp.getEnd_date();
		
		
		/*
		 * if(endDate.before(startDate)){
		 * System.out.println("StartDate is before EndDate"); return false; }
		 */
		
		if(isUnique(leaveapp.getEmployee().getId(),startDate,endDate)) {
			return true;
		}
		/*
		 * int duration = countDuration(startDate,endDate);
		 * 
		 * if(leavebal.getLeaveentitlement().getType().equals(LeaveType.MEDICAL)) {
		 * 
		 * if(leavebalance < duration)
		 * 
		 * System.out.println("Insufficient Medical Leave"); return false; }
		 * if(leavebal.getLeaveentitlement().getType().equals(LeaveType.ANNUAL)) {
		 * 
		 * if(leavebalance < (duration - countNumWeekends(startDate,endDate)))
		 * 
		 * return false; }
		 */	
		return false;
	}
	
	@Override
	public boolean isUnique(int id,Date startDate,Date endDate) {

		List<LeaveApplication> leaveList = larepo.findLeaveApplicationsByEmployeeId(id);
		
		for (Iterator<LeaveApplication> iterator = leaveList.iterator(); iterator.hasNext();) {
			LeaveApplication leaveApplication = (LeaveApplication) iterator.next();		
			
			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			System.out.println("Start Date: "+dateFormat.format(leaveApplication.getStart_date()));
			System.out.println("End Date: " + dateFormat.format(leaveApplication.getEnd_date()));
			
			if(!(leaveApplication.getStatus().equals(Status.CANCELLED) ||
					leaveApplication.getStatus().equals(Status.REJECTED)
					)) {
				
				//leaveApplication.getEnd_date().equals(endDate)
				
				if(leaveApplication.getStart_date().equals(startDate)) {
					System.out.println("Condition0");
					return false;
				}

				else if(endDate.before(leaveApplication.getStart_date())){
					System.out.println("Condition1");
					return false;
				}
				else if(!(startDate.after(leaveApplication.getEnd_date()))){
					System.out.println("Condition2");
					return false;		
				}
			}		
		}
		System.out.println("Current Leave Application is unique!");	
		return true;
	}
	
	public int countNumWeekends(Date d1, Date d2) {

		int count = 0;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);

		while(c2.after(c1)) {
			if(c1.get(Calendar.DAY_OF_WEEK) ==Calendar.SATURDAY || c1.get(Calendar.DAY_OF_WEEK) ==Calendar.SUNDAY) {
				count++;
			}
		}
		return count;
	}
	
	public int countDuration(Date d1, Date d2) {
		
		long diffInmillies = Math.abs(d1.getTime()-d2.getTime());
		long daysBetween = TimeUnit.DAYS.convert(diffInmillies,TimeUnit.MILLISECONDS);
		int duration = (int)daysBetween;
		if (duration == 0)
			return duration = 1;
		return duration;
	}
	
	@Transactional
	public void addLeaveApplication(LeaveApplication leaveapp) {
		larepo.save(leaveapp);

	}

	@Override
	public ArrayList<LeaveApplication> findAllLeaveApplications() {
		return (ArrayList<LeaveApplication>) larepo.findAll();
	}

	@Override
	public LeaveApplication findLeaveApplicationById(Integer id) {
		
		return larepo.findById(id).get();
	}

	@Transactional
	public void deleteLeaveApplication(LeaveApplication leaveApplication) {
		 larepo.delete(leaveApplication);
		
	}

	@Override
	public ArrayList<LeaveApplication> findAllLeaveApplicationByEmployeeId(Integer id) {
		
		return (ArrayList<LeaveApplication>) larepo.findLeaveApplicationsByEmployeeId(id);	}

}
