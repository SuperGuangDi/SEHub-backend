package com.scut.se.sehubbackend.Config;

import com.scut.se.sehubbackend.Security.Authentication.JWTPreAuthenticatedProcessingFilter;
import com.scut.se.sehubbackend.Security.Authentication.UsernamePasswordAuthenticationProcessingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired JWTPreAuthenticatedProcessingFilter jwtPreAuthenticatedProcessingFilter;
    @Autowired UsernamePasswordAuthenticationProcessingFilter usernamePasswordAuthenticationProcessingFilter;
    @Autowired WebConfig webConfig;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests().antMatchers("/api/**").authenticated()
                .and()
                .addFilterAt(jwtPreAuthenticatedProcessingFilter, AbstractPreAuthenticatedProcessingFilter.class)
                .addFilterAt(usernamePasswordAuthenticationProcessingFilter, UsernamePasswordAuthenticationFilter.class)
                .anonymous().disable()
                .csrf().disable();
//                .formLogin()
//                    .usernameParameter(webConfig.getUsernameParameter()).passwordParameter(webConfig.getPasswordParameter())
//                    .loginPage(webConfig.getLoginPage());

    }

}
