package com.telerikacademy.ngpuppies.config;

import com.telerikacademy.ngpuppies.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "UserServiceImpl")
    private UserDetailsService userDetailsService;
	
	// Config security datasource from AppConfig
	@Autowired
	private DataSource securityDataSource;
	
	// Config password encoder (bcrypt)
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception{
        return new JwtAuthenticationFilter();
    }

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
	
	// Config how to check username, password, whether user is enabled (1) and username's role (in db role should be kept as "ROLE_USER", "ROLE_ADMIN", etc. for this to work properly)
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(securityDataSource)
				.usersByUsernameQuery(
						"select username,password, enabled  from users where username=? COLLATE latin1_general_cs")
				.authoritiesByUsernameQuery(
						"select\n" +
								"\n" +
								"a.Username,\n" +
								"b.name as role\n" +
								"\n" +
								"from users as a\n" +
								"inner join roles as b\n" +
								"on a.roleId = b.roleId where username=?");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
				
				http.authorizeRequests()
						.antMatchers("/api/login").permitAll()
						.antMatchers("/api/client/**").hasRole("USER")
						.antMatchers("/client/**").hasRole("USER")
						.antMatchers("/admin/**").hasRole("ADMIN")
						.antMatchers("/api/admin/**").hasRole("ADMIN")
						//.antMatchers("/admin/admins/**").hasIpAddress("127.0.0.1")
						.anyRequest().authenticated()
				.and()
						.formLogin()
						.loginPage("/login")
						.loginProcessingUrl("/authenticateUser")
						/*.defaultSuccessUrl("/home")*/
				.and()
						.logout()
				.and()
						.exceptionHandling().accessDeniedPage("/access-denied").and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        http.cors().and().csrf().disable().httpBasic(); // added only for the purposes of testing with Postman (remove afterwards); stops more complicated authentication processes, which use tokens

    }
}
