package com.example.back.jwt

import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JwtSecurityConfig(private val tokenProvider: TokenProvider) :
    SecurityConfigurerAdapter<DefaultSecurityFilterChain?, HttpSecurity>() {
    override fun configure(http: HttpSecurity) {
        val customFilter = JwtFilter(tokenProvider)
        val singUpFilter = SingUpFilter(tokenProvider)
        http.addFilterBefore(singUpFilter,UsernamePasswordAuthenticationFilter::class.java)
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}