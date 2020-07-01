  
package nus.iss.edu.leave.config;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Admin;
import com.example.demo.model.AdminUserDetails;
import com.example.demo.repo.AdminRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	AdminRepository arepo; 
	
	//this username will return an instance of the user details
	//Spring will then authenticate against the instance here.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Admin> admin = arepo.findByUsername(username);
		admin.orElseThrow(()-> new UsernameNotFoundException("Not Found: "+ username));
		return admin.map(AdminUserDetails::new).get(); //converts into a AdminUserdetails instance
	}  
}
