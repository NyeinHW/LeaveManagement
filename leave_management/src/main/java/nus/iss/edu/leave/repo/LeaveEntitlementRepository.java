package nus.iss.edu.leave.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import nus.iss.edu.leave.model.LeaveEntitlement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import nus.iss.edu.leave.model.Role;

public interface LeaveEntitlementRepository extends JpaRepository<LeaveEntitlement, Integer> {
  
	public List<LeaveEntitlement> findByLeavetype(LeaveType lt);

}
