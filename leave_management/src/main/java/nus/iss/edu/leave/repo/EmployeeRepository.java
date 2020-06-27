package nus.iss.edu.leave.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import nus.iss.edu.leave.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
