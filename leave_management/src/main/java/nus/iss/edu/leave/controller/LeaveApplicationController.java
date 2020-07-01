package nus.iss.edu.leave.controller;

import javax.net.ssl.SSLEngineResult.Status;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import nus.iss.edu.leave.service.LeaveApplicationService;
import nus.iss.edu.leave.service.LeaveApplicationServiceImpl;

@Controller
@RequestMapping("employee/leave_form")
public class LeaveApplicationController {
	@Autowired
	private LeaveApplicationService lservice;
	@Autowired
	public void setLeaveApplicationService(LeaveApplicationServiceImpl lserviceImpl) {
		this.lservice = lserviceImpl;
	}
	@RequestMapping(value = "/lists")
	public String list(Model model) {
		model.addAttribute("leaveApplications", lservice.findAllLeaveApplications());
		return "leave-applications";
	}
	
	@RequestMapping(value = "/list")
	public String listByEmpId(Model model,HttpServletRequest request) {
        int empid = (Integer) request.getAttribute("empid");
		System.out.println("id is"+empid);
		model.addAttribute("leaveApplications", lservice.findAllLeaveApplicationByEmployeeId(empid));
		model.addAttribute("status",Status.values());
		model.addAttribute("empid",empid);
		
		return "leave-applications";
	}
    
}
