package nus.iss.edu.leave.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import nus.iss.edu.leave.model.LeaveApplication;

public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Integer> {

}
