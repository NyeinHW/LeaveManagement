package nus.iss.edu.leave.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nus.iss.edu.leave.model.Employee;
import nus.iss.edu.leave.model.LeaveApplication;
import nus.iss.edu.leave.model.Role;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	List<Employee> findAllEmployeesByName(String name);
	
	Employee findEmployeeByName(String name);
	
	List<Employee> findByRole(Role role);
	
	
	@Query(value="Select * from employee l where l.manager_id = :maneger_id",nativeQuery=true)
	List<Employee> findByManagerid(@Param("id") int id);
	
	@Query(value="Select * from employee l where l.doh = :doh",nativeQuery=true)
	List<Employee> findByHistoryTime(@Param("doh") Date doh);
	

}
