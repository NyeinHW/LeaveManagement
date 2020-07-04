package nus.iss.edu.leave.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import nus.iss.edu.leave.model.Employee;
import nus.iss.edu.leave.model.LeaveBalance;
import nus.iss.edu.leave.model.LeaveBalanceIdentity;
import nus.iss.edu.leave.model.LeaveEntitlement;
import nus.iss.edu.leave.model.Role;
import nus.iss.edu.leave.repo.EmployeeRepository;
import nus.iss.edu.leave.repo.LeaveBalanceRepository;
import nus.iss.edu.leave.repo.LeaveEntitlementRepository;
import nus.iss.edu.leave.repo.LeaveTypeRepository;

@Controller
public class LeaveEntitlementController {
	
	@Autowired
	private LeaveEntitlementRepository lrepo;
	
	@Autowired
	private EmployeeRepository erepo;
	
	@Autowired
	private LeaveTypeRepository leaverepo;
	
	@Autowired
	private LeaveBalanceRepository brepo;
	
	@Autowired
	public void setLrepo(LeaveEntitlementRepository lrepo) {
		this.lrepo = lrepo;
	}
	
	public void setErepo(EmployeeRepository erepo) {
		this.erepo = erepo;
	}

	public void setBrepo(LeaveBalanceRepository brepo) {
		this.brepo = brepo;
	}
	
	@RequestMapping(path="leaveentitlement",method=RequestMethod.GET)
	public String leaveentitlement(Model model)
	{
		
		model.addAttribute("leavelist",lrepo.findAll());
		return "leaveentitlement";
	}
	
	@RequestMapping(path = "leaveentitlement/add", method = RequestMethod.GET)
    public String createLeaveType(Model model) {
        model.addAttribute("leaveentitlement", new LeaveEntitlement());
        model.addAttribute("roles",Role.values());
        return "AddLeaveType";
    }
	
	@RequestMapping(path = "/save", method = RequestMethod.GET)
    public ModelAndView saveLeaveType(@Valid LeaveEntitlement leave,BindingResult bindingresult,Model model) {
		if(bindingresult.hasErrors())
		{
			return new  ModelAndView("AddLeaveType");
		}
		//System.out.println("Before check leave object");
		int leaveTypeId = leaverepo.getLeaveType(leave.getMykey().getLeavetype().getType());
		List<LeaveEntitlement> llist=lrepo.checkLeave(leaveTypeId,leave.getMykey().getRole().ordinal());
		//System.out.println("List size--->"+llist.size());
		leave.getMykey().setLeavetype(null);
		String message = null;
		if(llist.size() != 0)
		{
			 model.addAttribute("leaveentitlement", new LeaveEntitlement());
			 model.addAttribute("roles",Role.values());
			model.addAttribute("Error", "error");
			model.addAttribute("Message","Data already exists,So updated instead");
			message = "Data already exists,So updated instead";
			//System.out.println("Deleteing ---------------"+llist.get(0).getId());
			lrepo.deleteById(llist.get(0).getId());
		}
		leave.setLeaveId(leaveTypeId);
		lrepo.save(leave);
	    List<Employee> elist=erepo.findByRole(leave.getMykey().getRole());
        for(Employee e:elist)
    	{
        	System.out.println("before saving leave balance object");
    		LeaveBalance lbal=new LeaveBalance(new LeaveBalanceIdentity(e.getId(),leave.getId()),leave.getMykey().getMaxnoofdays());
    		brepo.save(lbal);
    		System.out.println("after saving leave blance object");
    	}
 
       
        return new ModelAndView("redirect:/leaveentitlement", "Message", message);
    }
	
	@RequestMapping(path = "/leaveentitlement/edit/{id}", method = RequestMethod.GET)
    public String editLeaveType(Model model, @PathVariable(value = "id") Integer id) {   	
    	LeaveEntitlement l = lrepo.findById(id).orElse(null);
        model.addAttribute("leaveentitlement", l);
        return "AddLeaveType";
    }

    @RequestMapping(path = "/leaveentitlement/delete/{id}", method = RequestMethod.GET)
    public String deleteLeaveType(@PathVariable(name = "id") Integer id) {
    	
        lrepo.delete(lrepo.findById(id).orElse(null));
        return "redirect:/leaveentitlement";
    }

}
