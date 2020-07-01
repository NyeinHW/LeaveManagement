package nus.iss.edu.leave.controller;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.error.RecordNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.model.Role;
import com.example.demo.repo.EmployeeRepository;
import com.example.demo.service.EmployeeServiceImpl;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
    	EmployeeServiceImpl service;
	@Autowired 
	EmployeeRepository emprepo;
	
	@GetMapping("/panel")
	public String admin(){
		System.out.print("Reached controller");
		return "admin-home";
	}
	
	@GetMapping("/viewemployees")
	public String getAllEmployees(Model model) {
		
		List<Employee> list = service.getAllEmployees();
		List<String> names = service.getAllManagerNames(list);
        model.addAttribute("employees", list);
		model.addAttribute("managers", names);
		
        return "adminlist-employee";
	}
	
	 @RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
	    public String createOrUpdateEmployee(Employee employee, @RequestParam(name= "manager_id")
	 	Integer managerid ) 

	    {
		 	Employee manager; 
	        manager = emprepo.findById(managerid).orElse(new Employee());
	        if(managerid != 0) employee.setManager(manager);
	        else employee.setManager(null);
	        //System.out.println(employee.getRole());
	        service.createOrUpdateEmployee(employee);
	        return "redirect:/admin/viewemployees";
	    }
	
	  @RequestMapping(path = {"/edit", "/edit/{id}"})
	    public String editEmployeeById(Model model, @PathVariable("id") Optional<Integer> id) 
	                            throws RecordNotFoundException 
	    {
		  //System.out.println("EditEmployees Controller Reached");
	      List<Employee> managers = emprepo.findByRole(Role.MANAGER);
	      model.addAttribute("managers", managers);
	      //model.addAttribute("standardDate", new Date());
	      Role[] roles = Role.values();
	      model.addAttribute("roles", roles); 
		  if (id.isPresent()) {
	            Employee entity = service.getEmployeeById(id.get());
	            model.addAttribute("employee", entity);	  	      	
	        } else {
	            model.addAttribute("employee", new Employee());
	        }
	        return "add-edit-employee";
	    }
	  
	  @RequestMapping(path="/delete/{id}")
	  public String deleteEmployeeById(@PathVariable("id") int id) {
		  service.deleteEmployeeById(id);
		  return "redirect:/admin/viewemployees";
	  }
	
}
