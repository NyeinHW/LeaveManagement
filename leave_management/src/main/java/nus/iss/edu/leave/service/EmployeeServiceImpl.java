package nus.iss.edu.leave.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nus.iss.edu.leave.model.Employee;
import nus.iss.edu.leave.repo.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	
	@Autowired
	EmployeeRepository erepo;
	
	@Override
	public ArrayList<Employee> findAll() {
		return (ArrayList<Employee>)erepo.findAll();
	}

	@Override
	public boolean saveEmployee(Employee emp) {
		if (erepo.save(emp)!=null) return true; else return false;
	}

	@Override
	public void deleteEmployee(Employee emp) {
		erepo.delete(emp);
	}


	@Override
	public ArrayList<String> findAllEmployeeNames(String name) {
		ArrayList<String> names = new ArrayList<String>();
		List<Employee> emplist = erepo.findAllEmployeesByName(name);
		for (Iterator<Employee> iterator = emplist.iterator(); iterator.hasNext();) {
			Employee employee = (Employee) iterator.next();
			names.add(employee.getName());
		}
		return names;
	}

	@Override
	public Employee findEmployeeById(Integer id) {
		return erepo.findById(id).get();
	}

	@Override
	public Employee findEmployeeByUsername(String name) {
		return erepo.findEmployeeByUsername(name);
	}
	
	  @Override
	    public List<String> getAllManagerNames(List<Employee> emp){
	    	
	    	List<String> emp_manager = new ArrayList<>();
	    	for (Employee e : emp) {
	    		Employee m = e.getManager();
	    		if (m != null) emp_manager.add(m.getName());
	    		else emp_manager.add("-");
	    	}
	    	return emp_manager; 
	    }
	  
	  @Override
	    public Employee createOrUpdateEmployee(Employee entity) 
	    {
	    	System.out.println("createOrUpdateEmployee in service layer called");

	    	Optional<Employee> employee = erepo.findById(entity.getId());
	    	System.out.println(entity.getDoh());
	        
	    	if(!employee.isPresent())
	        {
	    		entity = erepo.save(entity);            
	            return entity;
	        } 
	        
	        //if you press edit button
	        else if(employee.isPresent()) 
	            {
	                Employee newEntity = employee.get();
	                newEntity.setEmail(entity.getEmail());
	                newEntity.setName(entity.getName());
	                newEntity.setContact_no(entity.getContact_no());
	                newEntity.setDoh(entity.getDoh());
	                newEntity.setRole(entity.getRole());
	                newEntity.setPassword(entity.getPassword());
	                newEntity = erepo.save(newEntity);
	                 
	                return newEntity;
	            } 
	        return entity;
	    } 
	  
	  @Override
	    public void deleteEmployeeById(int id) //throws RecordNotFoundException 
	    {
	        Optional<Employee> employee = erepo.findById(id);
	         
	        if(employee.isPresent()) 
	        {
	            erepo.deleteById(id);
	        } //else {
	           // throw new RecordNotFoundException("No employee record exist for given id");
	        //}
	    } 

}
