package nus.iss.edu.leave.repo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nus.iss.edu.leave.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	List<Employee> findAllEmployeesByName(String name);
	
	Employee findEmployeeByUsername(String name);
		List<Employee> findByRole(Role role);

	@Query(value="Select * from leaveApplication l INNER JOIN l.employee le where le.manager = :manager_id",nativeQuery=true)
	ArrayList<Employee> findByManagerid(@Param("id") int id);

}

