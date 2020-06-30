package nus.iss.edu.leave.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import nus.iss.edu.leave.model.LeaveEntitlement;

public interface LeaveEntitlementRepo extends JpaRepository<LeaveEntitlement, Integer> {
	
}
