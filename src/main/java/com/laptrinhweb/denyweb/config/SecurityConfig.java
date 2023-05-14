package com.laptrinhweb.denyweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.laptrinhweb.denyweb.service.CustomerUserDetailService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomerUserDetailService customUserDetailService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
			.antMatchers("/admin/**", "/login/**", "/web/**", "/api/**").permitAll()
			.antMatchers("/quan-tri/**").hasRole("ADMIN")
			
			//.and().exceptionHandling().accessDeniedPage("/t")
			.and()
			.formLogin().loginPage("/dang-nhap").permitAll()
			.defaultSuccessUrl("/trang-chu/")
			.successHandler((request, response, authentication) -> {
                if (isAdmin(authentication)) {
                    response.sendRedirect("/quan-tri/trang-chu");
                } else {
                    response.sendRedirect("/");
                }
            })
			.failureUrl("/dang-nhap?sucess=fail")
			.usernameParameter("username")
            .passwordParameter("password")
			.loginProcessingUrl("/j_spring_security_check");
		http
	    	.sessionManagement()
		        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
		        .invalidSessionUrl("/dang-nhap")
		        .sessionFixation().migrateSession()
		        .maximumSessions(1)
		        .expiredUrl("/dang-nhap?expired")
		        .maxSessionsPreventsLogin(false)
		        .and();
		http
	        .logout()
		        .logoutRequestMatcher(new AntPathRequestMatcher("/thoat"))
		        .logoutSuccessUrl("/dang-nhap")
		        .deleteCookies("JSESSIONID");
		http
		.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
//		auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder().encode("123"))
//				.authorities("ROLE_ADMIN");
	}
	
	@Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl(); // Ta lưu tạm remember me trong memory (RAM). Nếu cần mình có thể lưu trong database
        return memory;
    }
	private boolean isAdmin(Authentication authentication) {
	    return authentication.getAuthorities().stream()
	            .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
	}
}
