package nus.iss.edu.leave.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nus.iss.edu.leave.model.Employee;
import nus.iss.edu.leave.model.Role;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	List<Employee> findAllEmployeesByName(String name);
	
	Employee findEmployeeByUsername(String name);
		List<Employee> findByRole(Role role);
		
		List<Employee> findByManager(Employee manager);
		
		@Query(value="select * from employee where emp_id=?1",nativeQuery=true)
		List<Employee> findAllById(int id);

}

