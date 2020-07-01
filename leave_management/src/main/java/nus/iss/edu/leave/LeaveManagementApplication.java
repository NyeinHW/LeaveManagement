package nus.iss.edu.leave;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;

import nus.iss.edu.leave.model.Employee;
import nus.iss.edu.leave.model.LeaveBalance;
import nus.iss.edu.leave.model.LeaveEntitlement;
import nus.iss.edu.leave.model.LeaveType;
import nus.iss.edu.leave.model.Role;
import nus.iss.edu.leave.repo.EmployeeRepository;
import nus.iss.edu.leave.repo.LeaveBalanceRepository;
import nus.iss.edu.leave.repo.LeaveEntitlementRepository;




@SpringBootApplication
public class LeaveManagementApplication {
	
	@Autowired
	EmployeeRepository erepo;
	
	@Autowired
	LeaveEntitlementRepository lerepo;
	
	@Autowired
	LeaveBalanceRepository lbrepo;
	
	public static void main(String[] args) {
		SpringApplication.run(LeaveManagementApplication.class, args);
	}
	
	
	  @Bean public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
	  return args ->{
	  
	  Employee e1 = new Employee(); 
	  e1.setAddress("aaaa");
	  e1.setContact_no(95185999);	  
	  e1.setName("sam");
	  e1.setPassword("1235679800"); 
	  e1.setEmail("acbc@gmail.com");
	  e1.setRole(Role.MANAGER);
	  e1.setManager_id(1);
	  erepo.save(e1);
	  
	  LeaveEntitlement le1 = new LeaveEntitlement();
	  LeaveEntitlement le2 = new LeaveEntitlement();
	  
	  le1.setLeave_count(18);
	  le1.setRole(Role.MANAGER);
	  le1.setType(LeaveType.ANNUAL);
	  
	  le2.setLeave_count(60);
	  le2.setRole(Role.MANAGER);
	  le2.setType(LeaveType.MEDICAL);
	  
	  lerepo.save(le1); lerepo.save(le2);
	  
	  LeaveBalance lb1 = new LeaveBalance(1,e1,le1,10);
	  LeaveBalance lb2 = new LeaveBalance(2,e1,le2,10);
	  
	  
	  lbrepo.save(lb1); lbrepo.save(lb2);
	  
	  };
	  
	  }
	 
}