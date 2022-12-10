package com.course.trucks.security.config;


import com.course.trucks.security.filter.JwtFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String USER_ROLE = "Driver";
    public static final String ADMIN_ROLE = "Admin";

    private final JwtFilter jwtFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)

                .and()

                .authorizeRequests()

                .antMatchers(HttpMethod.POST, "/api/cars").hasRole(ADMIN_ROLE)
                .antMatchers(HttpMethod.PATCH, "/api/cars/*").hasRole(ADMIN_ROLE)

                .antMatchers(HttpMethod.POST, "/api/tasks").hasRole(ADMIN_ROLE)
                .antMatchers(HttpMethod.PATCH, "/api/tasks/*").hasAnyRole(USER_ROLE, ADMIN_ROLE)
                .antMatchers(HttpMethod.GET, "/api/tasks", "/api/tasks/*").hasAnyRole(USER_ROLE, ADMIN_ROLE)
                .antMatchers(HttpMethod.DELETE, "/api/tasks/*").hasRole(ADMIN_ROLE)

                .antMatchers(HttpMethod.POST, "/api/users/**").hasRole(ADMIN_ROLE)
                .antMatchers(HttpMethod.PATCH, "/api/users/*").hasAnyRole(USER_ROLE, ADMIN_ROLE)
                .antMatchers(HttpMethod.PATCH, "/api/users/**").hasAnyRole(USER_ROLE, ADMIN_ROLE)
                .antMatchers(HttpMethod.GET, "/api/users", "/api/users/*", "/api/users/**").hasAnyRole(USER_ROLE, ADMIN_ROLE)
                .antMatchers(HttpMethod.DELETE, "/api/users/*").hasRole(ADMIN_ROLE)

                .antMatchers(HttpMethod.POST, "/api/auth/*").permitAll()

                .anyRequest().hasRole(ADMIN_ROLE)
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    protected AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }
}
