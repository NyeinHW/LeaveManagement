package nus.iss.edu.leave.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import nus.iss.edu.leave.model.LeaveEntitlement;

public interface LeaveEntitlementRepository extends JpaRepository<LeaveEntitlement, Integer> {
	
	@Query(value="select * from leaveentitlement where id=?1",nativeQuery=true)
	List<LeaveEntitlement> getLeaveById(int id);
	
	@Query(value="select * from leaveentitlement where leave_id=?1 and role=?2",nativeQuery=true)
	List<LeaveEntitlement> checkLeave(int leavetypeid,int rolId);

}
