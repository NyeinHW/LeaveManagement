package nus.iss.edu.leave.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import nus.iss.edu.leave.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	List<Employee> findAllEmployeesByName(String name);
	
	Employee findEmployeeByName(String name);
}
