package com.bmsit.bmsitapi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //set your configuration on auth object
        auth.inMemoryAuthentication()
                .withUser("ambrish")
                .password("1234")
                .roles("ADMIN")
                .and()
                .withUser("sandeep")
                .password("12345")
                .roles("USER");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        this method sets the authority to the user group
        ADMMIN user with higher privilage should be set first and then with least privilage user
        and the login method should be specified. in this case formlogin() is set
        there are some alternatives available
         */
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
                .antMatchers("/").permitAll()
                .and().formLogin();
    }
}
