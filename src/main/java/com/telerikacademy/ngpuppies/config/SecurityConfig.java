package com.telerikacademy.ngpuppies.config;

import com.telerikacademy.ngpuppies.security.FirstLoginInterceptor;
import com.telerikacademy.ngpuppies.security.models.JwtAuthenticationFilter;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

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

    @Bean
    public FirstLoginInterceptor firstLoginInterceptor(){
        return new FirstLoginInterceptor();
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public JwtAuthenticationFilter authenticationTokenFilterBean() throws Exception {
        return new JwtAuthenticationFilter();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // Config how to check username, password, whether user is enabled (1) and username's role (in db role should be kept as "ROLE_USER", "ROLE_ADMIN", etc. for this to work properly)
    // If enabled = 0 => can't pass login
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
                .anyRequest().authenticated()
                .and()
                .formLogin()
                /*.loginPage("/login")
                .loginProcessingUrl("/authenticateUser")
                *//*.defaultSuccessUrl("/home")*/
                .and()
                .logout()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied").and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
        http.cors().and().csrf().disable().httpBasic(); // added only for the purposes of testing with Postman (remove afterwards); stops more complicated authentication processes, which use tokens

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(firstLoginInterceptor()).addPathPatterns("/api/admin/**");

    }
}
