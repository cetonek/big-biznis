package com.github.cetonek.bigbiznis.application

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
class WebSecurityConfiguration : WebSecurityConfigurerAdapter() {

    private val ACTUATOR_ROLE = "ACTUATOR"

    // authorization who has access to what
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .mvcMatchers("/actuator/**").hasRole(ACTUATOR_ROLE)
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .and()
                .httpBasic()
    }
}