package nus.iss.edu.leave.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import nus.iss.edu.leave.model.Employee;
import nus.iss.edu.leave.model.LeaveApplication;
import nus.iss.edu.leave.model.LeaveEntitlement;
import nus.iss.edu.leave.model.Status;
import nus.iss.edu.leave.repo.LeaveEntitlementRepository;
import nus.iss.edu.leave.service.EmployeeService;
import nus.iss.edu.leave.service.EmployeeServiceImpl;
import nus.iss.edu.leave.service.LeaveApplicationService;
import nus.iss.edu.leave.service.LeaveApplicationServiceImpl;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {
	
	@Autowired
	protected LeaveEntitlementRepository lerepo;
	
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
		if(employee != null)
		{
			if(emp.getPassword().equals(employee.getPassword())){
				System.out.println("id"+employee.getId());
		        request.setAttribute("param1", employee.getId());

				return "forward:/employee/leave_form/list"; 
			}
			else {
				return "employee-login";
			}
		}
		//include error page.
		return "employee-login";
	}

	@RequestMapping(value = "/applyLeave") 
	public String applyLeave (@ModelAttribute("leaveapplication") LeaveApplication la,HttpServletRequest request ) {
		int empid = (int)request.getAttribute("empid");
		la.setEmployee(empservice.findEmployeeById(empid));
		return "leave-form"; 
	}
	
	@RequestMapping(value = "/submitLeave")
	public String submitLeave (@ModelAttribute ("leaveapplication") @Valid LeaveApplication la, BindingResult bindingResult, Model model){
		if (bindingResult.hasErrors()) {
			return "leave-form";
		}
		Employee emp = empservice.findEmployeeById(la.getEmployee().getId());
		la.setEmployee(emp);
		
		LeaveEntitlement leaveEntitlementResult = new LeaveEntitlement();

		List<LeaveEntitlement> leaveEntitlement = lerepo.findAll();
		for (Iterator<LeaveEntitlement> iterator = leaveEntitlement.iterator(); iterator.hasNext();) {
			LeaveEntitlement leaveentitle = (LeaveEntitlement) iterator.next();
			System.out.println(leaveentitle);
			if(leaveentitle.getRole() == la.getEmployee().getRole() && leaveentitle.getType()==la.getLeaveentitlement().getType())
			{
				leaveEntitlementResult = leaveentitle;
			}
		}
		
		System.out.println(la);
		System.out.println(la.getLeaveentitlement().getType());
		System.out.println(la.getEmployee());
		
		if(laservice.leaveValidation(la))
		{
			la.setStatus(Status.APPLIED);
			la.setLeaveentitlement(leaveEntitlementResult);
			laservice.addLeaveApplication(la);
			return "forward:/employee-leavelist";
		}
		else return "leave-form";
	}

}
