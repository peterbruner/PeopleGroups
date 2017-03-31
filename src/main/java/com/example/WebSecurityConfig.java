package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    //@Override
    protected void configure(HttpSecurity http, AuthenticationManagerBuilder auth) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/","/index").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("bob").password("pass").roles("USER");
        auth.inMemoryAuthentication().withUser("sally").password("pass").roles("USER");
    }

//    @Component( "restAuthenticationEntryPoint" )
//    public class RestAuthenticationEntryPoint
//            implements AuthenticationEntryPoint {
//
//        @Override
//        public void commence(
//                HttpServletRequest request,
//                HttpServletResponse response,
//                AuthenticationException authException) throws IOException {
//
//            response.sendError( HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized" );
//        }
//    }
}
