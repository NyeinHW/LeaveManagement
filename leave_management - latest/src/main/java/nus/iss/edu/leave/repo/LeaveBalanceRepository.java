package nus.iss.edu.leave.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import nus.iss.edu.leave.model.LeaveBalance;

public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Integer> {
	
	@Query (value="SELECT * FROM LeaveBalance lb WHERE lb.employee_id = :empid AND lb.leaveentitlement_id = :leid" , nativeQuery=true)
	public LeaveBalance findLeaveBalance(@Param("empid") Integer empid, @Param("leid") Integer leid);
	
}
