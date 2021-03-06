package com.test.config;

import com.test.config.imp.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebMVCConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.
//                authorizeRequests().anyRequest().permitAll().and().formLogin().permitAll();
        http.csrf().disable();

        http.authorizeRequests().
                antMatchers("/api/user/delete/*").hasAuthority("OWNER").
                antMatchers("/api/user/add-to-teacher/*").hasRole("OWNER").
                antMatchers("/api/user/get").authenticated().
                antMatchers("/api/user/all").hasAuthority("OWNER").
                antMatchers("/api/user/add").permitAll().
                antMatchers("/api/result/save").authenticated().
                antMatchers("/api/subject/add").hasAnyAuthority("OWNER", "TEACHER").
                antMatchers("/api/test/add").hasAnyAuthority("OWNER", "TEACHER").
                antMatchers("/api/test/questions").hasAnyAuthority("OWNER", "TEACHER").
                antMatchers("/api/test/delete/*").hasAuthority("OWNER").
                antMatchers("/api/user/teacher/*").hasAuthority("OWNER").
                anyRequest().permitAll().and().formLogin().permitAll().and().logout().permitAll().logoutSuccessUrl("/");
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected MyUserDetailsService userDetailsService() {
        return new MyUserDetailsService();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }
}
