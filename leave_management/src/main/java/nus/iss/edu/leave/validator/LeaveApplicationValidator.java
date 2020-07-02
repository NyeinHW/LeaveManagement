package nus.iss.edu.leave.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import nus.iss.edu.leave.model.LeaveApplication;
import nus.iss.edu.leave.service.LeaveApplicationService;
import nus.iss.edu.leave.service.LeaveApplicationServiceImpl;

public class LeaveApplicationValidator implements Validator {

	
	/*
	 * @Autowired LeaveApplicationService laservice;
	 * 
	 * 
	 * public void setlaservice (LeaveApplicationServiceImpl laserviceimp) {
	 * this.laservice = laserviceimp; };
	 */
	
	@Override
	public boolean supports(Class<?> clazz) {
		return LeaveApplication.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LeaveApplication leaveapp = (LeaveApplication) target;
		
		if(leaveapp.getEnd_date().before(leaveapp.getStart_date())){		
			errors.rejectValue("start_date", "date_chronologically.error.msg");
		}
		/*
		 * if(!laservice.leaveValidation(leaveapp)) { errors.rejectValue("start_date",
		 * "date_validation.error.msg"); }
		 */
	}

}
