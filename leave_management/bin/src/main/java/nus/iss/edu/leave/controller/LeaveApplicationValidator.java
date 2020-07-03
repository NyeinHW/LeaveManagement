package nus.iss.edu.leave.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import nus.iss.edu.leave.model.LeaveApplication;

public class LeaveApplicationValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return LeaveApplication.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LeaveApplication leaveapp = (LeaveApplication) target;
		if(leaveapp.getEnd_date().before(leaveapp.getStart_date())){		
			errors.rejectValue("start_date", "Start date MUST not be before End date");
		}
	}

}
