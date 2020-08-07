package com.todungnguyen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Configuration
@EnableWebSecurity // kích hoạt việc tích hợp Spring Security với Spring MVC
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	// gọi tới UserDetailService để cấu hình
	@Autowired
	private UserDetailsService userDetailsService;

	// interface PasswordEncoder đảm nhiệm việc mã hoá mật khẩu bằng thuật toán
	// BCrypt
	// Muốn sử dụng nó, phải cấu hình nó thành 1 bean
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	// cấu hình các chị tiết về bảo mật
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				// thêm dòng này để có thể dùng REST  
				// tính năng bảo mật chống giả mạo request => chỉ cho dùng GET => k thể dùng
				// REST
				.csrf().disable()
				// Phân quyền request
				.authorizeRequests()
				// antMatchers() khai báo đường dẫn của request
				// permitAll() cho phép tất cả các user truy cập
				.antMatchers("/").permitAll()

				// hasRole(role) chỉ cho các user có GrantedAuthority là ROLE_role truy cập
				.antMatchers("/studentpage").hasRole("STUDENT")
				.antMatchers("/teacherpage").hasRole("TEACHER")
				.antMatchers("/admin").hasRole("ADMIN")

				// anyRequest().authenticated() các request còn lại phải xác thực
				.anyRequest().authenticated()
				.and()
				.httpBasic();
	}
}
