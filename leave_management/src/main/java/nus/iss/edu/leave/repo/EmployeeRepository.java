package nus.iss.edu.leave.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import nus.iss.edu.leave.model.Employee;
import nus.iss.edu.leave.model.Role;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	List<Employee> findAllEmployeesByName(String name);
	Employee findEmployeeByName(String name);
	List<Employee> findByRole(Role role);
// 	Optional<Employee> findByUsername(String Username);
	Employee findEmployeeByUsername(String name);

}
