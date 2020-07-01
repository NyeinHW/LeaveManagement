package nus.iss.edu.leave.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import nus.iss.edu.leave.model.Employee;
import nus.iss.edu.leave.model.LeaveApplication;
import nus.iss.edu.leave.repo.LeaveEntitlementRepository;
import nus.iss.edu.leave.service.EmployeeService;
import nus.iss.edu.leave.service.EmployeeServiceImpl;
import nus.iss.edu.leave.service.LeaveApplicationService;
import nus.iss.edu.leave.service.LeaveApplicationServiceImpl;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

	@Autowired
	protected EmployeeService empservice;
	
	@Autowired
	public void setEmployeeService (EmployeeServiceImpl empserviceim)
	{
		this.empservice = empserviceim;
	}
	
	@Autowired
	protected LeaveApplicationService laservice;
	
	@Autowired
	public void setLeaveApplicationService (LeaveApplicationServiceImpl laserviceim)
	{
		this.laservice = laserviceim;
	}
	
	@Autowired
	protected LeaveEntitlementRepository levEntRepo;
	

	@GetMapping(value = "/login")
	public String loginPage(@ModelAttribute("employee") Employee emp) {
		return "employee-login";
	}

	@RequestMapping(value = "/submit")
	public String loginPage(@ModelAttribute ("employee") @Valid Employee emp, BindingResult result, Model model,HttpServletRequest request) {

		if (result.hasErrors())
			return "employee-login";
		
		Employee employee = empservice.findEmployeeByName(emp.getName());
		System.out.println(emp);
		System.out.println(employee);
		
		if(employee != null)
		{
			System.out.println("Wrong employee!");
			System.out.println(emp.getPassword());
			System.out.println(employee.getPassword());
			
			if(emp.getPassword().equals(employee.getPassword())){
				System.out.println("id"+employee.getId());
		        request.setAttribute("param1", employee.getId());

				return "forward:/employee/leave_form/list"; 
			}
			else {
				System.out.println("employee == null");
				return "employee-login";
			}
		}
		return "employee-login";
	}
	
	@RequestMapping(value = "/applyLeave2") 
	public String applyLeave2 () {
		return "employee-leavelist"; 
	}

	@RequestMapping(value = "/applyLeave") 
	public String applyLeave (@ModelAttribute("leaveapplication") LeaveApplication la ) {
		return "leave-form"; 
	}
	
	@RequestMapping(value = "/submitLeave")
	public String submitLeave (@ModelAttribute ("leaveapplication") @Valid LeaveApplication la, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "leave-form";
		}
		if(laservice.leaveValidation(la))
		{
			laservice.addLeaveApplication(la);
			return "forward:/employee-leavelist";
		}
		else return "leave-form"; 
	}

}
