package nus.iss.edu.leave.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nus.iss.edu.leave.model.LeaveType;

public interface LeaveTypeRepository extends JpaRepository<LeaveType, Integer> {
	
	@Query (value="SELECT * FROM LeaveType lt WHERE lt.type = :name" , nativeQuery=true)
	public LeaveType findLeaveTypeByName(@Param("name") String name);
	
	public LeaveType findLeaveTypeById(Integer id);
}
