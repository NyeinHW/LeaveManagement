package nus.iss.edu.leave.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import nus.iss.edu.leave.model.LeaveEntitlement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import nus.iss.edu.leave.model.Role;

public interface LeaveEntitlementRepository extends JpaRepository<LeaveEntitlement, Integer> {
	
	/*
	 * @Query (value=
	 * "SELECT * FROM LeaveEntitlement le where le.role=:role AND le.type_id=:typeid"
	 * , nativeQuery=true) public LeaveEntitlement
	 * findLeaveEntitlementByType(@Param("role") Role role, @Param("typeid") int
	 * typeid);
	 */
}
