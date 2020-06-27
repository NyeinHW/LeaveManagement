package nus.iss.edu.leave.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import nus.iss.edu.leave.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
