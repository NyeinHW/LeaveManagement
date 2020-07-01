package nus.iss.edu.leave;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;

import nus.iss.edu.leave.model.Employee;
import nus.iss.edu.leave.model.LeaveApplication;
import nus.iss.edu.leave.model.LeaveEntitlement;
import nus.iss.edu.leave.model.LeaveType;
import nus.iss.edu.leave.model.Role;
import nus.iss.edu.leave.model.Status;
import nus.iss.edu.leave.repo.EmployeeRepository;
import nus.iss.edu.leave.repo.LeaveApplicationRepository;
import nus.iss.edu.leave.repo.LeaveEntitlementRepository;




@SpringBootApplication
public class LeaveManagementApplication {
	
	@Autowired
	EmployeeRepository erepo;
	@Autowired
	private LeaveApplicationRepository lrepo;
	@Autowired
	private LeaveEntitlementRepository lerepo;
	public static void main(String[] args) {
		SpringApplication.run(LeaveManagementApplication.class, args);
	}
	
	
	  @Bean public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
	  return args ->{
	  
		  long millis=System.currentTimeMillis();  
			java.util.Date date=new java.util.Date(millis);  
				LeaveEntitlement le1=new LeaveEntitlement(LeaveType.ANNUAL,Role.MANAGER,12);
				Employee e1=new Employee("nyein","123456789",1,"n@gmail.com","natogyi",Role.MANAGER,date,1);
				
			    LeaveApplication la1=new LeaveApplication("not",date,date,Status.APPLIED,"hate","hello",e1,le1);
			    
			    LeaveApplication la2=new LeaveApplication("hello",date,date,Status.APPROVED,"ok","hello",e1,le1);
			    LeaveApplication la3=new LeaveApplication("hello",date,date,Status.REJECTED,"ok","hello",e1,le1);

			    erepo.save(e1);
			    lerepo.save(le1);
			    lrepo.save(la1);
			    lrepo.save(la2);
			    lrepo.save(la3);

	  };
	  
	  }
	 
}