package nus.iss.edu.leave.controller;


import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("employee/leave_form")
public class LeaveApplicationController {
	
	@InitBinder protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new LeaveApplicationValidator());

	}
	
	@Autowired
	protected LeaveEntitlementRepository lerepo;
	
	@Autowired
	private LeaveApplicationService lservice;
	
	@Autowired
	public void setLeaveApplicationService(LeaveApplicationServiceImpl lserviceImpl) {
		this.lservice = lserviceImpl;
	}
	
	@Autowired
	protected EmployeeService empservice;

	@Autowired
	public void setEmployeeService (EmployeeServiceImpl empserviceim)
	{
		this.empservice = empserviceim;
	}

	@RequestMapping(value = "/lists")
	public String list(Model model) {
		model.addAttribute("leaveApplications", lservice.findAllLeaveApplications());
		return "leave-applications";
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
		
		if(lservice.leaveValidation(la))
		{
			la.setLeaveentitlement(leaveEntitlementResult);
			lservice.addLeaveApplication(la);
			request.setAttribute("empid", emp.getId());
			return "forward:/employee/leave_form/list";
		}
		else return "leave-form";
	}
	
	@RequestMapping(value = "/list")
	public String listByEmpId(Model model,HttpServletRequest request) {
        int empid = (Integer) request.getAttribute("empid");
		System.out.println("id is"+empid);
		model.addAttribute("leaveApplications", lservice.findAllLeaveApplicationByEmployeeId(empid));
		model.addAttribute("empid",empid);
		
		return "leave-applications";
	}
	
	@RequestMapping(value = "/edit/{leave_app_id}")
	public String updateLeave(Model model,@PathVariable("leave_app_id") Integer id) {
		LeaveApplication updateLeave = lservice.findLeaveApplicationById(id);
		updateLeave.setStatus(Status.UPDATED);
		model.addAttribute("leaveapplication", updateLeave);
		return "leave-form";
	}
	
	@RequestMapping(value = "/cancel/{leave_app_id}")
	public String cancelLeave(Model model,@PathVariable("leave_app_id") Integer id,HttpServletRequest request) {
		LeaveApplication la=lservice.findLeaveApplicationById(id);
		la.setStatus(Status.CANCELLED);
		request.setAttribute("empid", la.getEmployee().getId());
		model.addAttribute("leaveapplication", lservice.findLeaveApplicationById(id));
		return "forward:/employee/leave_form/list";
	}
	
	@RequestMapping(value = "/delete/{leave_app_id}")
	public String deleteLeave(Model model,@PathVariable("leave_app_id") Integer id,HttpServletRequest request) {

		LeaveApplication la=lservice.findLeaveApplicationById(id);
		request.setAttribute("empid", la.getEmployee().getId());
		
		lservice.deleteLeaveApplication(la);
		return "forward:/employee/leave_form/list";
	}
    
}
