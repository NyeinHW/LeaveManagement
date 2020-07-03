package nus.iss.edu.leave.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//import nus.iss.edu.leave.model.Admin;
//import com.example.demo.error.RecordNotFoundException;
import nus.iss.edu.leave.model.Employee;
import nus.iss.edu.leave.model.LeaveType;
import nus.iss.edu.leave.model.Role;
//import nus.iss.edu.leave.repo.AdminRepository;
import nus.iss.edu.leave.repo.EmployeeRepository;
import nus.iss.edu.leave.service.EmployeeService;
import nus.iss.edu.leave.service.EmployeeServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	protected EmployeeService empservice;

	@Autowired
	public void setEmployeeService (EmployeeServiceImpl empserviceim)
	{
		empservice = empserviceim;
	}	
	
	@Autowired 
	EmployeeRepository emprepo;
	
	@GetMapping(value = "/login")
	public String loginPage(Model model) {
		model.addAttribute("employee", new Employee());
		return "admin-login";
	}

	@PostMapping(value = "/login")
	public String login(@ModelAttribute ("employee") @Valid Employee employee, Model model) {

		System.out.print("test");
		
		Optional<Employee> e = emprepo.findByUsername(employee.getUsername());
		Role r = e.get().getRole();
		String usern = e.get().getUsername();
		System.out.print(usern + "   " + r);
			
		if(e.isPresent() && e.get().getRole() == Role.ADMIN )
		{
			if(e.get().getPassword().equals(employee.getPassword())){
				return "admin-home"; 
			}
			else return "admin-login";
		}
		return "admin-login";
	}
	
	@RequestMapping("/panel")
	public String admin(){
		System.out.print("Reached controller");
		return "admin-home";
	}
	
	@GetMapping("/viewemployees")
	public String getAllEmployees(Model model) {
		
		List<Employee> list = empservice.findAll();
		List<String> names = empservice.getAllManagerNames(list);
        model.addAttribute("employees", list);
		model.addAttribute("managers", names);
		
        return "adminlist-employee";
	}
	
	 @RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
	    public String createOrUpdateEmployee(@ModelAttribute("employee") @Valid Employee employee, @RequestParam(name= "manager_id")
	 	Integer managerid ) 

	    {
	        Employee manager = emprepo.findById(managerid).orElse(new Employee());
	        System.out.print(employee.getDoh());
	        if(managerid != 0) employee.setManager(manager);
	        else employee.setManager(null);
	        empservice.createOrUpdateEmployee(employee);
	        return "redirect:/admin/viewemployees";
	    }
	
	  @RequestMapping(path = {"/edit", "/edit/{id}"})
	    public String editEmployeeById(Model model, @PathVariable("id") Optional<Integer> id) 
	    {
	      List<Employee> managers = emprepo.findByRole(Role.MANAGER);
	      model.addAttribute("managers", managers);
	      Role[] roles = Role.values();
	      model.addAttribute("roles", roles); 
		  if (id.isPresent()) {
	            Employee entity = empservice.findEmployeeById(id.get());
	            model.addAttribute("employee", entity);	  	      	
	        } else {
	            model.addAttribute("employee", new Employee());
	        }
	        return "add-edit-employee";
	    }
	  
	  @RequestMapping(path="/delete/{id}")
	  public String deleteEmployeeById(@PathVariable("id") int id) {
		  empservice.deleteEmployeeById(id);
		  return "redirect:/admin/viewemployees";
	  }
	  
	  @RequestMapping("/manage-leavetype")
	  public String manageLeaveType(Model model) {		  		  
		  //model.addAttribute("leavetype", leavetype);
		  return "manage-leave-type";
	  }
	  
	  @PostMapping("/manage-leavetype")
	  public String submitLeaveType(@RequestParam("newleavetype") String newleave, LeaveType leavetype) {
		  
		  return "redirect:/admin/viewemployees";
	  }
	
}