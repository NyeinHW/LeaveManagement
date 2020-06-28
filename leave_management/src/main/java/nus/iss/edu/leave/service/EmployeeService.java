package nus.iss.edu.leave.service;

import java.util.ArrayList;
import nus.iss.edu.leave.model.Employee;

public interface EmployeeService {
	public ArrayList<Employee> findAll();
	public boolean saveEmployee(Employee emp);
	public void deleteEmployee(Employee emp);
	public ArrayList<String> findAllEmployeeNames(String name);
	public Employee findEmployeeById(Integer id);
	public Employee findEmployeeByName(String name);
}
