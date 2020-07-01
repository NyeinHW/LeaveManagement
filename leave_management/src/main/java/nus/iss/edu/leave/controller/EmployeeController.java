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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
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
	
	/*
	 * @InitBinder protected void initBinder(WebDataBinder binder) {
	 * binder.addValidators(new LeaveApplicationValidator());
	 * 
	 * }
	 */

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
	public String login(@ModelAttribute ("employee") @Valid Employee emp, BindingResult result, Model model,HttpServletRequest request) {

		if (result.hasErrors())
			return "employee-login";

		Employee employee = empservice.findEmployeeByName(emp.getName());
		if(employee != null)
		{
			if(emp.getPassword().equals(employee.getPassword())){
				
				request.setAttribute("empid", employee.getId());

				return "forward:/employee/leave_form/list"; 
			}
			else {
				return "employee-login";
			}
		}
		//include error page.
		return "employee-login";
	}

	@RequestMapping(value = "/applyLeave/{empid}") 
	public String applyLeave (@PathVariable ("empid") Integer id,  @ModelAttribute("leaveapplication") LeaveApplication la) {
		int empid = (Integer) id;
		la.setStatus(Status.APPLIED);
		la.setEmployee(empservice.findEmployeeById(id));
		return "leave-form"; 
	}

	@RequestMapping(value = "/submitLeave")
	public String submitLeave (@ModelAttribute ("leaveapplication") @Valid LeaveApplication la, BindingResult bindingResult, Model model, HttpServletRequest request){
		if (bindingResult.hasErrors()) {
			return "leave-form";
		}
		Employee emp = empservice.findEmployeeById(la.getEmployee().getId());
		la.setEmployee(emp);

		LeaveEntitlement leaveEntitlementResult = new LeaveEntitlement();

		List<LeaveEntitlement> leaveEntitlement = lerepo.findAll();
		for (Iterator<LeaveEntitlement> iterator = leaveEntitlement.iterator(); iterator.hasNext();) {
			LeaveEntitlement leaveentitle = (LeaveEntitlement) iterator.next();
			if(leaveentitle.getRole() == la.getEmployee().getRole() && leaveentitle.getType()== la.getLeaveentitlement().getType())
			{
				leaveEntitlementResult = leaveentitle;
			}
		}
		
		if(laservice.leaveValidation(la))
		{
			la.setLeaveentitlement(leaveEntitlementResult);
			laservice.addLeaveApplication(la);
			request.setAttribute("empid", emp.getId());
			return "forward:/employee/leave_form/list";
		}
		else return "leave-form";
	}
	
	@RequestMapping(value = "/leave-form/edit/{leave_app_id}")
	public String updateLeave(Model model,@PathVariable("leave_app_id") Integer id) {
		LeaveApplication updateLeave = laservice.findLeaveApplicationById(id);
		updateLeave.setStatus(Status.UPDATED);
		model.addAttribute("leaveapplication", updateLeave);
		return "leave-form";
	}
	
	@RequestMapping(value = "/leave-form/cancel/{leave_app_id}")
	public String cancelLeave(Model model,@PathVariable("leave_app_id") Integer id,HttpServletRequest request) {
		LeaveApplication la=laservice.findLeaveApplicationById(id);
		la.setStatus(Status.CANCELLED);
		request.setAttribute("empid", la.getEmployee().getId());
		model.addAttribute("leaveapplication", laservice.findLeaveApplicationById(id));
		return "forward:/employee/leave_form/list";
	}
	
	@RequestMapping(value = "/leave-form/delete/{leave_app_id}")
	public String deleteLeave(Model model,@PathVariable("leave_app_id") Integer id,HttpServletRequest request) {

		LeaveApplication la=laservice.findLeaveApplicationById(id);
		request.setAttribute("empid", la.getEmployee().getId());
		
		laservice.deleteLeaveApplication(la);
		return "forward:/employee/leave_form/list";
	}
	
}
		
