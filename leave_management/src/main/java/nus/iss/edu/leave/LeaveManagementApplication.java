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
import nus.iss.edu.leave.repo.LeaveTypeRepository;

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

	@Autowired
	LeaveTypeRepository ltrepo;

	public static void main(String[] args) {
		SpringApplication.run(LeaveManagementApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			
			  LeaveType medical = new LeaveType("Medical", 60);
			  LeaveType annual = new LeaveType("Annual",14); 
			  LeaveType compensation = new LeaveType("Compensation",20);
			  
			  ltrepo.save(medical); ltrepo.save(annual); ltrepo.save(compensation);
			  System.out.println("End leave type creation");
			  
			  long millis=System.currentTimeMillis(); java.util.Date date=new
			  java.util.Date(millis);
			  
			  LeaveEntitlement le5 = new LeaveEntitlement(annual,Role.MANAGER,12);
			  LeaveEntitlement le1 = new LeaveEntitlement(annual,Role.STAFF,18); 
			  LeaveEntitlement le2 = new LeaveEntitlement(medical,Role.MANAGER,60);
			  LeaveEntitlement le3 = new LeaveEntitlement(medical,Role.STAFF,60);
			  
			  Employee e1 = new Employee("nyein","nyeinuser","123456789",1,"n@gmail.com","natogyi",Role.MANAGER,date,1);
			  Employee e2 = new Employee("sam","samnuser","1235679800",95185999,"acbc@gmail.com","AMK",Role.STAFF,date,1); 
			  
			  LeaveApplication la1=new LeaveApplication("not",date,date,Status.UPDATED,"hate","hello",e1,le5);
			  LeaveApplication la2=new LeaveApplication("hello",date,date,Status.APPROVED,"ok","hello",e1,le5);
			  LeaveApplication la3=new LeaveApplication("hello",date,date,Status.REJECTED,"ok","hello",e1,le5);
			  
			  LeaveBalance lb1 = new LeaveBalance(1,e2,le1,10); 
			  LeaveBalance lb2 = new LeaveBalance(2,e2,le2,10);
			  
			  
			  System.out.println("start employee creation");
			  erepo.save(e1); erepo.save(e2);
				  
			  System.out.println("start leave entitlement creation");
			  
			  lerepo.save(le5); lerepo.save(le2); lerepo.save(le1); lerepo.save(le3);
			 
			  System.out.println("start leave application creation");
			  
			  lrepo.save(la1); lrepo.save(la2); lrepo.save(la3);
			  
			  System.out.println("start leave balance creation");

			  lbrepo.save(lb1); lbrepo.save(lb2);
			  

			  


		};

	}

}