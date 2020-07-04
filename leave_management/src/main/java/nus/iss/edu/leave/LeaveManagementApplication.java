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
	
	  @Bean public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
	  return args ->{
		  LeaveType medical = new LeaveType("Medical", 60);
		  LeaveType annual = new LeaveType("Annual",14); 
		  LeaveType compensation = new LeaveType("Compensation",20);
		  
		  ltrepo.save(medical); ltrepo.save(annual); ltrepo.save(compensation);
		  System.out.println("End leave type creation");
		  long millis=System.currentTimeMillis();  
		  java.util.Date date=new java.util.Date(millis);  
		  LeaveEntitlement le5=new LeaveEntitlement(medical,Role.STAFF,12);
		  Employee e1=new Employee("John","123456789", "john-sales", 1,"john_sales@gmail.com","Sunset Avenue",Role.MANAGER,date);

		  LeaveApplication la1=new LeaveApplication("not",date,date,Status.UPDATED,"not sure","hello",e1,le5);

		  LeaveApplication la2=new LeaveApplication("hello",date,date,Status.APPROVED,"ok","hello",e1,le5);
		  LeaveApplication la3=new LeaveApplication("hello",date,date,Status.REJECTED,"ok","hello",e1,le5);

		  erepo.save(e1);
		  lerepo.save(le5);
		  lrepo.save(la1);
		  lrepo.save(la2);
		  lrepo.save(la3);

		  Employee e2 = new Employee(); 
		  e2.setAddress("Brick Lane");
		  e2.setUsername("sam-sales");
		  e2.setContact_no(95185999);	  
		  e2.setName("sam");
		  e2.setPassword("1235679800"); 
		  e2.setEmail("acbc@gmail.com");
		  e2.setRole(Role.STAFF);
		  e2.setManager(e1);
		  erepo.save(e2);

		  LeaveEntitlement le1 = new LeaveEntitlement();
		  LeaveEntitlement le2 = new LeaveEntitlement();

		  le1.setLeave_count(18);
		  le1.setRole(Role.STAFF);
		  le1.setType(annual);

		  le2.setLeave_count(60);
		  le2.setRole(Role.STAFF);
		  le2.setType(medical);

		  lerepo.save(le1); lerepo.save(le2);

		  LeaveBalance lb1 = new LeaveBalance(1,e2,le1,10);
		  LeaveBalance lb2 = new LeaveBalance(2,e2,le2,10);


		  lbrepo.save(lb1); lbrepo.save(lb2);
		  
		  Employee e3 = new Employee("Dorothy", "nyein", "123456789", 92743748, "dorothy-admin@gmail.com", "Lucky Avenue", Role.STAFF, date);
		  Employee e4 = new Employee("Dorothy", "hsu", "123456789", 92743748, "dorothy-admin@gmail.com", "Lucky Avenue", Role.ADMIN, date);
		  Employee e5 = new Employee("wai", "wai", "123456789", 92743748, "dorothy-admin@gmail.com", "Lucky Avenue", Role.MANAGER, date);

		  erepo.save(e3);
		  erepo.save(e4);
		  erepo.save(e5);

	  };
	  
	  }
}