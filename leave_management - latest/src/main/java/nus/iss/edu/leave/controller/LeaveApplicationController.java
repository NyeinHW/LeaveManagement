package nus.iss.edu.leave.controller;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
import nus.iss.edu.leave.model.LeaveType;
import nus.iss.edu.leave.model.Status;
import nus.iss.edu.leave.repo.LeaveEntitlementRepository;
import nus.iss.edu.leave.service.EmployeeService;
import nus.iss.edu.leave.service.EmployeeServiceImpl;
import nus.iss.edu.leave.service.LeaveApplicationService;
import nus.iss.edu.leave.service.LeaveApplicationServiceImpl;
import nus.iss.edu.leave.service.LeaveTypeService;
import nus.iss.edu.leave.service.LeaveTypeServiceImpl;
import nus.iss.edu.leave.validator.LeaveApplicationValidator;

@Controller
@RequestMapping("employee/leave_form")
public class LeaveApplicationController {
	
	@InitBinder protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new LeaveApplicationValidator());

	}
	
	@Autowired
	public LeaveTypeService ltservice;
	
	public void setLeaveTypeService (LeaveTypeServiceImpl ltserviceimp) {
		this.ltservice = ltserviceimp;
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
	
	@Autowired
    private JavaMailSender javaMailSender;

	@RequestMapping(value = "/lists")
	public String list(Model model) {
		model.addAttribute("leaveApplications", lservice.findAllLeaveApplications());
		return "leave-applications";
	}
	
	@RequestMapping(value = "/submitLeave")
	public String submitLeave (@ModelAttribute ("leaveapplication") @Valid LeaveApplication la, BindingResult bindingResult, Model model, HttpServletRequest request){
		
		ArrayList<LeaveType> leaveTypes = ltservice.findAllLeaveType();
		model.addAttribute("ltypes", leaveTypes);
		
		if (bindingResult.hasErrors()) {
			return "leave-form";
		}
		Employee emp = empservice.findEmployeeById(la.getEmployee().getId());
		la.setEmployee(emp);

		LeaveEntitlement leaveEntitlementResult = new LeaveEntitlement();
		
		System.out.println("la is "+la.getLeaveentitlement().getType().getType());
		
		List<LeaveEntitlement> leaveEntitlement = lerepo.findAll();
		for (Iterator<LeaveEntitlement> iterator = leaveEntitlement.iterator(); iterator.hasNext();) {
			LeaveEntitlement leaveentitle = (LeaveEntitlement) iterator.next();
			
			System.out.println(leaveentitle);
			
			if(leaveentitle.getRole() == la.getEmployee().getRole() && leaveentitle.getType().getType().equals(la.getLeaveentitlement().getType().getType()))
			{
				leaveEntitlementResult = leaveentitle;
			}
		}
		
		
		System.out.println(" leaveEntitlementResult "+leaveEntitlementResult);
		
		if(lservice.leaveValidation(la))
		{
			request.setAttribute("empid", emp.getId());
			la.setLeaveentitlement(leaveEntitlementResult);
			lservice.addLeaveApplication(la);
			sendApplicationEmail(emp.getManager(), la);
			return "forward:/employee/leave_form/list";
		}
		else {
			model.addAttribute("empid",emp.getId());
			return "error";
		}		
	}
	
	
	@RequestMapping(value = "/view/{leave_app_id}")
	public String viewLeave(Model model,@PathVariable("leave_app_id") Integer id) {
		model.addAttribute("leaveapplication", lservice.findLeaveApplicationById(id));
		System.out.println("Detail is"+lservice.findLeaveApplicationById(id));
		return "leave-form-detail";
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
		ArrayList<LeaveType> leaveTypes = ltservice.findAllLeaveType();
		model.addAttribute("ltypes", leaveTypes);
		return "update-leave-app-by-emp";
	}
	
	@RequestMapping(value = "/cancel/{leave_app_id}")
	public String cancelLeave(Model model,@PathVariable("leave_app_id") Integer id,HttpServletRequest request) {
		LeaveApplication la=lservice.findLeaveApplicationById(id);
		lservice.cancelLeaveApplication(la);
		request.setAttribute("empid", la.getEmployee().getId());
		model.addAttribute("leaveapplication", lservice.findLeaveApplicationById(id));
		return "forward:/employee/leave_form/list";
	}
	
	@RequestMapping(value = "/delete/{leave_app_id}")
	public String deleteLeave(Model model,@PathVariable("leave_app_id") Integer id,HttpServletRequest request) {

		LeaveApplication la=lservice.findLeaveApplicationById(id);
		lservice.deleteLeaveApplication(la);
		request.setAttribute("empid", la.getEmployee().getId());
		model.addAttribute("leaveapplication", lservice.findLeaveApplicationById(id));
		return "forward:/employee/leave_form/list";
//		lservice.deleteLeaveApplication(la);
	}
	
    public void sendApplicationEmail(Employee manager, LeaveApplication la) {
    	
    	String email = manager.getEmail();
    	String AppId = Integer.toString(la.getId());
    	String applicant = la.getEmployee().getName(); 
    		SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(email);

            msg.setSubject("[NOTIFICATION] Leave Application #" + AppId);
            msg.setText(applicant + " has submitted a leave application for your approval.");
            javaMailSender.send(msg);
    	}
        
    }
	

    

