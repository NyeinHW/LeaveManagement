  
package nus.iss.edu.leave.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
    
	@Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder getPasswordEncorder() { return NoOpPasswordEncoder.getInstance();}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/admin/**").hasRole("ADMIN")       
            .antMatchers("/login").permitAll()
            .and().formLogin()
            .defaultSuccessUrl("/admin/panel", true);
        
            /*.anyRequest()
            .hasRole("ADMIN")
            .and()
            .formLogin()
            .loginPage("/admin/login")
            .failureUrl("/admin/login?error=loginError")
            .defaultSuccessUrl("/admin/panel")
            .and()
            .logout()
            .logoutUrl("/admin_logout")
            .logoutSuccessUrl("/protectedLinks");*/
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        //Will call a UDS object and use it to Authenticate the input 
    }
}
