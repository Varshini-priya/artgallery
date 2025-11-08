package com.project.artgallery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.project.artgallery.entity.AppRole;
import com.project.artgallery.entity.Users;
import com.project.artgallery.repository.UsersRepository;
import com.project.artgallery.security.AuthEntryPointJwt;
import com.project.artgallery.security.AuthTokenFilter;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
 
	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
 
	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
 
		http.csrf(csrf -> csrf.disable())
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.authorizeHttpRequests(
						auth -> auth
						.requestMatchers("/api/auth/**").permitAll()
						.requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
						.requestMatchers("/api/admin/**").hasRole("ADMIN")
						.requestMatchers("/api/artist/**").hasRole("ARTIST")
						.requestMatchers("/api/user/**", "/api/category/**", "/api/fav/**").hasAnyRole("USER", "ADMIN", "ARTIST")
						.requestMatchers("/api/public/**").permitAll()
						.anyRequest().authenticated()
 
				);
		 http.exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler));
	     
		http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));
 
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
 
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
		return builder.getAuthenticationManager();
	}
	
	@Bean
	public CorsFilter corsFilter() {
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    config.addAllowedOrigin("http://localhost:3000");
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("*");
	    source.registerCorsConfiguration("/**", config);
	    return new CorsFilter(source);
	}
	
 
 
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return web -> web.ignoring().requestMatchers("/v2/api-docs",
				"/configuration/ui", "/swagger-resources/**",
				"/configuration/security", "/swagger-ui.html", "/webjars/**");
	}
	
	@Bean
	public CommandLineRunner initDefaultAdmin(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
	    return args -> {
	        if (!usersRepository.existsByUserName("admin")) {
	            Users admin = new Users(
	                "admin",
	                "admin@example.com",
	                passwordEncoder.encode("adminPass")
	            );
	            
	            // Assign ADMIN role
	            Set<AppRole> roles = new HashSet<>();
	            roles.add(AppRole.ROLE_ADMIN);
	            admin.setRoles(roles);
 
	            usersRepository.save(admin);
	            System.out.println("Default ADMIN user created: admin / adminPass");
	        } else {
	            System.out.println("Admin user already exists. Skipping creation.");
	        }
	    };
	}
	
	
	  
	  //coors 
	  
	  @Bean
		public WebMvcConfigurer corsConfigurer() {
			return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(CorsRegistry registry) {
					registry.addMapping("/**")
					 		.allowedOriginPatterns("*") // allows any origin
							.allowedOrigins("http://localhost:3000")
							.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").allowedHeaders("*")
							.allowCredentials(true);
				}
			};
		}

}
