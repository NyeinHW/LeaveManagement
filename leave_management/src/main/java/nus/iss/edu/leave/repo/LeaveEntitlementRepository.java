package nus.iss.edu.leave.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import nus.iss.edu.leave.model.LeaveEntitlement;

public interface LeaveEntitlementRepository extends JpaRepository<LeaveEntitlement, Integer> {

}
