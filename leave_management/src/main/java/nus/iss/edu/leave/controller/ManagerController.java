package nus.iss.edu.leave.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import nus.iss.edu.leave.model.Employee;
import nus.iss.edu.leave.model.LeaveApplication;
import nus.iss.edu.leave.repo.EmployeeRepository;
import nus.iss.edu.leave.repo.LeaveApplicationRepository;



@Controller
@RequestMapping(value = "/manager")
public class ManagerController extends EmployeeController {

	@Autowired
	EmployeeRepository emRepo;

	@Autowired
	LeaveApplicationRepository laRepo;
	

	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/application")
	public String listAll(Model model, LeaveApplication leaveApplication) {
		model.addAttribute("leaveApplications", laRepo.findAll());
		return "manager-view-application";
	}

	@GetMapping("/approve/{id}")
	public String showEditForm(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("leaveApplication", laRepo.findById(id).get());
		return "forward:/manager/manager-view-application";
	}

	@GetMapping("/reject/{id}")
	public String deleteMethod(Model model, @PathVariable("id") Integer id) {
		LeaveApplication la = laRepo.findById(id).get();
		laRepo.delete(la);
		return "forward:/manager/manager-view-application";
	}
	
	@GetMapping("/list")
	public String showhistory(Model model, Employee em) {
		model.addAttribute("Employee", emRepo.findByManagerid(em.getManager_id()));
		return "manager-view-list";
	}
	
	@GetMapping("/listnow")
	public String shownow(Model model, Employee em) {
		model.addAttribute("Employee", emRepo.findByHistoryTime(em.getDoh()));
		return "manager-view-list";
	}
}
