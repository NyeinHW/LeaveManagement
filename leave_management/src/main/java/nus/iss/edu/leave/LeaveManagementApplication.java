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

import nus.iss.edu.leave.model.LeaveApplication;
import nus.iss.edu.leave.model.LeaveBalance;
import nus.iss.edu.leave.model.LeaveEntitlement;
import nus.iss.edu.leave.model.LeaveType;
import nus.iss.edu.leave.model.Role;
import nus.iss.edu.leave.model.Status;
import nus.iss.edu.leave.repo.EmployeeRepository;
import nus.iss.edu.leave.repo.LeaveApplicationRepository;
import nus.iss.edu.leave.repo.LeaveBalanceRepository;
import nus.iss.edu.leave.repo.LeaveEntitlementRepository;


@SpringBootApplication
public class LeaveManagementApplication {
	
	@Autowired
	EmployeeRepository erepo;
	@Autowired
	private LeaveApplicationRepository lrepo;
	@Autowired
	LeaveEntitlementRepository lerepo;
	
	@Autowired
	LeaveBalanceRepository lbrepo;
	

	public static void main(String[] args) {
		SpringApplication.run(LeaveManagementApplication.class, args);
	}
	
	
	  @Bean 
	  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
	  return args ->{

		  long millis=System.currentTimeMillis();  
		  java.util.Date date=new java.util.Date(millis);  
		  LeaveEntitlement le5=new LeaveEntitlement(LeaveType.ANNUAL,Role.MANAGER,12);
		  Employee e1=new Employee("nyein","123456789",1,"n@gmail.com","natogyi",Role.MANAGER,date,1);

		  LeaveApplication la1=new LeaveApplication("not",date,date,Status.UPDATED,"hate","hello",e1,le5);

		  LeaveApplication la2=new LeaveApplication("hello",date,date,Status.APPROVED,"ok","hello",e1,le5);
		  LeaveApplication la3=new LeaveApplication("hello",date,date,Status.REJECTED,"ok","hello",e1,le5);

		  erepo.save(e1);
		  lerepo.save(le5);
		  lrepo.save(la1);
		  lrepo.save(la2);
		  lrepo.save(la3);

		  Employee e2 = new Employee(); 
		  e2.setAddress("aaaa");
		  e2.setContact_no(95185999);	  
		  e2.setName("sam");
		  e2.setPassword("1235679800"); 
		  e2.setEmail("acbc@gmail.com");
		  e2.setRole(Role.MANAGER);
		  erepo.save(e2);

		  LeaveEntitlement le1 = new LeaveEntitlement();
		  LeaveEntitlement le2 = new LeaveEntitlement();

		  le1.setLeave_count(18);
		  le1.setRole(Role.MANAGER);
		  le1.setType(LeaveType.ANNUAL);

		  le2.setLeave_count(60);
		  le2.setRole(Role.MANAGER);
		  le2.setType(LeaveType.MEDICAL);

		  lerepo.save(le1); lerepo.save(le2);

		  LeaveBalance lb1 = new LeaveBalance(1,e2,le1,10);
		  LeaveBalance lb2 = new LeaveBalance(2,e2,le2,10);


		  lbrepo.save(lb1); lbrepo.save(lb2);
	  
	  };
	  
	  }
	 
}