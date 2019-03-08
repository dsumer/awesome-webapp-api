package com.awesome.webapp.api

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfiguration : WebSecurityConfigurerAdapter(false) {
    override fun configure(http: HttpSecurity?) {
        if (http != null) {
            http
                    // TODO: pay attention, csrf shouldn't be disabled in production6
                    .csrf().disable()
                    .authorizeRequests().anyRequest().permitAll()
                    .and()
                    .sessionManagement()
                    .maximumSessions(-1)
                    .expiredUrl("/")
        }
    }

    override fun configure(webSecurity: WebSecurity) {
        // permit all static resources
        webSecurity.ignoring()
                .antMatchers("/img/*")
                .antMatchers("/js/*")
                .antMatchers("/css/*")
                .antMatchers("/fonts/*")
    }
}
