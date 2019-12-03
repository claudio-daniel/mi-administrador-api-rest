package com.springboot.data.app;

import org.springframework.beans.factory.annotation.Autowired;

import com.springboot.data.app.auth.handler.LoginSuccessHandler;
import com.springboot.data.app.models.service.JpaUserDetailsService;

//@EnableGlobalMethodSecurity(securedEnabled=false )//habilitando prePostEnable=true se puede usar la anotacion @PreAuthorize
//@Configuration
public class SpringSecurityConfig{ // extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private LoginSuccessHandler loginSuccess;
	
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private JpaUserDetailsService userDetailsService;
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		
//		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/departamento/**", "/layout/**","/inquilino/**").permitAll()
//		.antMatchers("/direccion/**").hasAnyRole("ADMIN", "USER")
//		//.antMatchers("/edificio/**").hasAnyRole("ADMIN")
//		.antMatchers("/expensa/**").hasAnyRole("ADMIN")
//		.antMatchers("/factura/**").hasAnyRole("ADMIN")
//		//.antMatchers("/inquilino/**").hasAnyRole("ADMIN", "USER")
//		.antMatchers("/mantenimiento/**").hasAnyRole("ADMIN")
//		.antMatchers("/propietario/**").hasAnyRole("ADMIN")
//		.antMatchers("/servicio/**").hasAnyRole("ADMIN")
//		.anyRequest().authenticated()
//		.and()
//		.formLogin()
//		.successHandler(loginSuccess)
//		.loginPage("/login")
//		.permitAll()
//		.and()
//		.logout().permitAll()
//		.and()
//		.exceptionHandling().accessDeniedPage("/error_403")
//		;
//	}

//	@Autowired
//	public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
//
//		//autenticacion con jpa
//		builder.userDetailsService(userDetailsService)
//		.passwordEncoder(passwordEncoder);
//		
//		
//		//para autenticar con data sorce jdbc
//		//builder.jdbcAuthentication()
//		//.dataSource(dataSource)
//		//.passwordEncoder(passwordEncoder)
//		//.usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username=?")
//		//.authoritiesByUsernameQuery("SELECT u.username, a.authority FROM authorities a INNER JOIN users u ON u.id = a.user_id WHERE u.username=?");
//		
////		UserBuilder users = User.builder().passwordEncoder(encoder :: encode);
////		
////		builder.inMemoryAuthentication()
////		.withUser(users.username("admin").password("admin").roles("ADMIN", "USER"))
////		.withUser(users.username("claudio").password("clau").roles("USER"))
////		;
//	}

}
