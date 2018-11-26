package com.example.bee2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.bee2.service.UserService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserService userInfoService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/bee/login").defaultSuccessUrl("/bee/top").failureUrl("/bee/login?error=true").permitAll().and()
					.logout().logoutRequestMatcher(new AntPathRequestMatcher("/bee/logout")).logoutSuccessUrl("/bee/login").permitAll();
		http.authorizeRequests().antMatchers("/css/**", "/js/**", "/assets/**", "/bee/login/**", "/bee/regist/**").permitAll()
					.antMatchers("/bee/admin/**").hasRole("ADMIN")
					.anyRequest().authenticated();
	}
	
	@Autowired
	void configureAuthenticationManager(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userInfoService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
