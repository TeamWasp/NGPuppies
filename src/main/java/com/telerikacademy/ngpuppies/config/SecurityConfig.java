package com.telerikacademy.ngpuppies.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	// Config security datasource from AppConfig
	@Autowired
	private DataSource securityDataSource;
	
	// Config password encoder (bcrypt)
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// Config how to check username, password, whether user is enabled (1) and username's role (in db role should be kept as "ROLE_USER", "ROLE_ADMIN", etc. for this to work properly)
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(securityDataSource)
				.usersByUsernameQuery(
						"select username,password, enabled  from users where username=?")
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
		
		//http.csrf().disable();
		http.authorizeRequests()
				.antMatchers("/").hasAnyRole("USER", "ADMIN")
				.antMatchers("/clients/**").hasRole("USER")
				.antMatchers("/admin/**").hasRole("ADMIN")
				//.antMatchers("/admin/admins/**").hasIpAddress("127.0.0.1")
				//.anyRequest().permitAll()
				.and()
				.formLogin()
				//.loginProcessingUrl("/login")
				.permitAll();
	}
}
