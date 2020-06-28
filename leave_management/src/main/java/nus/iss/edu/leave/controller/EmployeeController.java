package nus.iss.edu.leave.controller;

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
import nus.iss.edu.leave.service.EmployeeService;
import nus.iss.edu.leave.service.EmployeeServiceImpl;

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
	@GetMapping(value = "/login")
	public String loginPage(@ModelAttribute("user") Employee emp) {
		emp = new Employee();
		return "employee-login";
	}
	
	@GetMapping(value = "/submit")
	public String login(@PathVariable("name") String name,@PathVariable("password") String password, @Valid Employee emp, BindingResult result, Model model) {
		if (result.hasErrors())
			return "employee-login";
		Employee employee = empservice.findEmployeeByName(name);
		if(employee == null)
		{
			model.addAttribute("ErrMsg","Invalid username!");
			return "employee-login"; 
		}
		if (password != employee.getPassword())
		{
			model.addAttribute("errMsg","Incorrect password!");
			return "employee-login";
		}
		
		return "employee-login";
		/* return "forward:/employee/viewLeaves"; */
	}
	
	/*
	 * @RequestMapping(value = "/viewLeaves") public String listleaves () {
	 * 
	 * return "list-leaves"; }
	 * 
	 * @RequestMapping(value = "/applyLeaves") public String applyLeaves () {
	 * 
	 * return "leave-form"; }
	 * 
	 * @RequestMapping(value = "/editLeaves/{id}") public String editLeaves () {
	 * 
	 * return "leave-form"; }
	 */
}
