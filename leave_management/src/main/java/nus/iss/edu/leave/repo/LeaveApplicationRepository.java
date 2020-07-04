package nus.iss.edu.leave.repo;

import java.util.ArrayList;
import java.util.Date;
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
	@Query(value="Select * FROM LeaveApplication l WHERE (l.start_date >= :start_date AND l.end_date <= :end_date)",nativeQuery=true)
    List<LeaveApplication> findByTime(@Param("start_date") Date date, @Param("end_date") Date date2);
 
  @Query(value="Select * from LeaveApplication l WHERE l.status IN (0,1)",nativeQuery=true)
    List<LeaveApplication> findByStatus();
  
  @Query(value="Select * from LeaveApplication l where l.status in (0,1) AND l.employee_id = :id and Year(l.start_date) = 2020",nativeQuery=true)
  List<LeaveApplication> findLeaveApplicationsByEmployeeIdAndStatus(@Param("id") int id);
}

