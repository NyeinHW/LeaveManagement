package nus.iss.edu.leave.controller;

 

import java.sql.Date;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import nus.iss.edu.leave.model.Employee;
import nus.iss.edu.leave.model.LeaveApplication;
import nus.iss.edu.leave.model.Status;
import nus.iss.edu.leave.repo.EmployeeRepository;
import nus.iss.edu.leave.repo.LeaveApplicationRepository;
import nus.iss.edu.leave.service.LeaveApplicationService;
import nus.iss.edu.leave.service.LeaveApplicationServiceImpl;
@Controller
@RequestMapping(value = "employee/manager")
public class ManagerController extends EmployeeController {
    @Autowired
    EmployeeRepository emRepo;
    @Autowired
    LeaveApplicationRepository laRepo;

	@Autowired
	protected LeaveApplicationService laservice;
	 
	@Autowired
	public void setLeaveApplicationService (LeaveApplicationServiceImpl laserviceim)
	{
		this.laservice = laserviceim;
	}

    @RequestMapping(value="/first")
    public String home(Model model,HttpSession session) {
		int empid = (Integer) session.getAttribute("managerId");
   	System.out.println("manager id is"+empid);
    	model.addAttribute("manager_id",empid);
        return "manager_index.html";
    }
    @GetMapping("/application/{id}")
    public String listForApprove(Model model,@PathVariable("id") Integer id) {
    	Employee manager=emRepo.findById(id).get();
    	List<Employee> e=emRepo.findByManager(manager);
    	ArrayList<LeaveApplication> leaveApplications=new ArrayList<LeaveApplication>();
    	for (Iterator iterator = e.iterator(); iterator.hasNext();) {
			Employee employee = (Employee) iterator.next();
			List<LeaveApplication> la=laRepo.findLeaveApplicationsByEmployeeIdAndStatus(employee.getId());
			leaveApplications.addAll(la);
		}
    	model.addAttribute("leaveApplications",leaveApplications);
        return "manager-view-application";
    }
    @GetMapping("/approve/{id}")
    public String listNew(Model model, @PathVariable("id") Integer id,HttpSession session) {
		int managerid = (Integer) session.getAttribute("managerId");

    	LeaveApplication la = laRepo.findById(id).get();
        laservice.approvedLeaveApplication(la);
        Date fromDate1 = new Date(System.currentTimeMillis());
        long from1 = fromDate1.getTime();  
        long to1 = la.getEnd_date().getTime();  
        int duration = (int) ((to1 - from1) / (1000 * 60 * 60 * 24));  
        Employee manager=emRepo.findById(managerid).get();
    	List<Employee> e=emRepo.findByManager(manager);
    	ArrayList<LeaveApplication> leaveApplications=new ArrayList<LeaveApplication>();
    	for (Iterator iterator = e.iterator(); iterator.hasNext();) {
			Employee employee = (Employee) iterator.next();
			List<LeaveApplication> la1=laRepo.findLeaveApplicationsByEmployeeIdAndStatus(employee.getId());
			leaveApplications.addAll(la1);
		}
    	model.addAttribute("leaveApplications",leaveApplications);
//        model.addAttribute("leaveApplications", laRepo.findByStatus());
        /*
         * List<LeaveApplication> result = laRepo.findByStatus();
         * System.out.println(result);
         */
        return "/manager-view-application.html";
    }

 

    @GetMapping("/reject/{id}")
    public String deleteMethod(Model model, @PathVariable("id") Integer id,HttpSession session) {

    	LeaveApplication la = laRepo.findById(id).get();
        laservice.rejectedLeaveApplication(la);
        model.addAttribute("leaveApplications", la);
        return "/reject-application.html";
    }

 

    @RequestMapping(value="/save", method=RequestMethod.POST)
    public String saveMethod( @ModelAttribute("leaveApplication") LeaveApplication leaveApplication,  Model model,HttpSession session) {
		int managerid = (Integer) session.getAttribute("managerId");

        System.out.println(leaveApplication);
        
        LeaveApplication la=laRepo.findById(leaveApplication.getId()).get();
        System.out.println("la is " + la);
        la.setManager_cmt(leaveApplication.getManager_cmt());
        laRepo.save(la);
        
        Employee manager=emRepo.findById(managerid).get();
    	List<Employee> e=emRepo.findByManager(manager);
    	ArrayList<LeaveApplication> leaveApplications=new ArrayList<LeaveApplication>();
    	for (Iterator iterator = e.iterator(); iterator.hasNext();) {
			Employee employee = (Employee) iterator.next();
			List<LeaveApplication> la1=laRepo.findLeaveApplicationsByEmployeeIdAndStatus(employee.getId());
			leaveApplications.addAll(la1);
		}
    	model.addAttribute("leaveApplications",leaveApplications);
         
        return "/manager-view-application.html";
    }
    @GetMapping("/list/{id}")
    public String showhistory(Model model,@PathVariable("id") Integer id, LeaveApplication leaveApplication) {
    	Employee manager=emRepo.findById(id).get();
    	List<Employee> e=emRepo.findByManager(manager);
    	ArrayList<LeaveApplication> leaveApplications=new ArrayList<LeaveApplication>();
    	for (Iterator iterator = e.iterator(); iterator.hasNext();) {
			Employee employee = (Employee) iterator.next();
			List<LeaveApplication> la=laRepo.findLeaveApplicationsByEmployeeId(employee.getId());
			leaveApplications.addAll(la);
		}
    	model.addAttribute("leaveApplications",leaveApplications); 
    	return "/manager-view-list.html";
    }

 

    @GetMapping("/listByTime/{start_date}/{end_date}")
    public String showSame(Model model,@PathVariable("start_date") Date start_date,@PathVariable("end_date") Date end_date) {
        
        List<LeaveApplication> result = laRepo.findByTime(start_date,end_date);
        System.out.println(result);
        
        model.addAttribute("leaveApplications", result);
        return "/manager-view-application.html";
    }
}
 