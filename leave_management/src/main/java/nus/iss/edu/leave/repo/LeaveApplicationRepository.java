package nus.iss.edu.leave.repo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nus.iss.edu.leave.model.Employee;
import nus.iss.edu.leave.model.LeaveApplication;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Integer> {
	@Query(value="Select * from LeaveApplication l where l.employee_id = :id and Year(l.start_date) = 2020",nativeQuery=true)
	  List<LeaveApplication> findLeaveApplicationsByEmployeeId(@Param("id") int id);
	
	@Query(value="Select * from LeaveApplication l INNER JOIN l.employee le where le.manager = :manager_id",nativeQuery=true)
	ArrayList<Employee> findByManagerid(@Param("manager_id") int id);

}
