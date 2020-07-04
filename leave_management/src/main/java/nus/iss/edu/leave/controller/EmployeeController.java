package nus.iss.edu.leave.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nus.iss.edu.leave.model.Employee;
import nus.iss.edu.leave.model.LeaveApplication;
import nus.iss.edu.leave.model.LeaveEntitlement;
import nus.iss.edu.leave.model.LeaveType;
import nus.iss.edu.leave.model.Role;
import nus.iss.edu.leave.model.Status;
import nus.iss.edu.leave.repo.LeaveEntitlementRepository;
import nus.iss.edu.leave.service.EmployeeService;
import nus.iss.edu.leave.service.EmployeeServiceImpl;
import nus.iss.edu.leave.service.LeaveApplicationService;
import nus.iss.edu.leave.service.LeaveApplicationServiceImpl;
import nus.iss.edu.leave.service.LeaveTypeService;
import nus.iss.edu.leave.service.LeaveTypeServiceImpl;

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
	public LeaveTypeService ltservice;
	
	public void setLeaveTypeService (LeaveTypeServiceImpl ltserviceimp) {
		this.ltservice = ltserviceimp;
	}
	
	@GetMapping(value = "/loginpage")
	public String loginPage(@ModelAttribute("employee") Employee emp) {
		return "employee-login";
	}

	@RequestMapping(value = "/login")
	public String login(@ModelAttribute ("employee") @Valid Employee emp, BindingResult result, Model model,HttpServletRequest request,HttpSession session) {

		if (result.hasErrors())
		{
			System.out.println("Invoke validator");
			return "employee-login";
		}

		Employee employee = empservice.findEmployeeByUsername(emp.getUsername());
		
		System.out.println("Found Emp:" + employee);
		if(employee != null)
		{
			System.out.println("hello hi "+emp);
			if(emp.getPassword().equals(employee.getPassword())){

				request.setAttribute("empid", employee.getId());
				
				if(employee.getRole()==Role.MANAGER) {
					System.out.println("it is manager");
					session.setAttribute("managerId", employee.getId());
					System.out.println("id is"+employee.getId());
					return "forward:/employee/manager/first";
				}
				if(employee.getRole()==Role.STAFF) {
					System.out.println("role is"+employee.getRole());
					return "forward:/employee/leave_form/list"; 
				}
			}		
		}
		System.out.println("Incorrect password");
		//include error page.
		return "forward:/employee/loginpage";
	}
//    @RequestMapping(value="/manager/first")
//    public String home(Model model,HttpServletRequest request) {
//		int manager_id = (Integer) request.getAttribute("managerId");
//   	System.out.println("manager id is"+manager_id);
//    	model.addAttribute("manager_id",manager_id);
//        return "manager_index";
//    }
	@RequestMapping(value = "/applyLeave/{empid}") 
	public String applyLeave (@PathVariable ("empid") Integer id,  @ModelAttribute("leaveapplication") LeaveApplication la,Model model) {
		int empid = (Integer) id; 
		la.setStatus(Status.APPLIED);
		la.setEmployee(empservice.findEmployeeById(id));
		ArrayList<LeaveType> leaveTypes = ltservice.findAllLeaveType();
		model.addAttribute("ltypes", leaveTypes);
		
		return "leave-form"; 
	}
	
	@RequestMapping(value = "/leave-form/view/{leave_app_id}")
	public String viewLeave(Model model,@PathVariable("leave_app_id") Integer id) {
//		LeaveApplication updateLeave = laservice.findLeaveApplicationById(id);
//		updateLeave.setStatus(Status.UPDATED);
//		model.addAttribute("leaveapplication", updateLeave);
		model.addAttribute("leaveapplication", laservice.findLeaveApplicationById(id));
		return "leave-form-detail";
	}
	
}
		
