package nus.iss.edu.leave.service;



import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.iss.edu.leave.model.LeaveApplication;
import nus.iss.edu.leave.model.LeaveBalance;
import nus.iss.edu.leave.model.LeaveType;
import nus.iss.edu.leave.repo.LeaveApplicationRepository;
import nus.iss.edu.leave.repo.LeaveBalanceRepository;

@Service
public class LeaveApplicationServiceImpl implements LeaveApplicationService {
	
	@Autowired
	LeaveApplicationRepository larepo;

	@Autowired
	LeaveBalanceRepository lbrepo;

	@Override
	public boolean leaveValidation(LeaveApplication leaveapp) {

		LeaveBalance leavebal = lbrepo.findLeaveBalance(leaveapp.getEmployee().getId(),leaveapp.getLeaveentitlement().getId());
		
		Date startDate = leaveapp.getStart_date();
		Date endDate = leaveapp.getEnd_date();
		int leavebalance = leavebal.getBalance();

		if(!startDate.before(endDate)){
			return false;
		}
		
		int duration = countDuration(startDate,endDate);

		if(leavebal.getLeaveentitlement().getType().equals(LeaveType.MEDICAL)) {

			if(leavebalance < duration)
				return false;
		}
		if(leavebal.getLeaveentitlement().getType().equals(LeaveType.ANNUAL)) {
			
			if(leavebalance < (duration - countNumWeekends(startDate,endDate)))

				return false;
		}
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
		return duration;
	}
	
	@Transactional
	public void addLeaveApplication(LeaveApplication leaveapp) {
		larepo.save(leaveapp);

	}

}
