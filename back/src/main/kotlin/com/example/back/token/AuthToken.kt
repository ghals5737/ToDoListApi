package com.example.back.token

import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority


class AuthToken(private val principal: String,private val credentials: Boolean?) : AbstractAuthenticationToken(ArrayList<GrantedAuthority>()) {
    override fun getCredentials(): Any? {
        return credentials
    }

    override fun getPrincipal(): Any {
        return principal
    }

    override fun getAuthorities(): MutableCollection<GrantedAuthority>? {
        val authorities = ArrayList<GrantedAuthority>()
        authorities.add(SimpleGrantedAuthority("USER"))
        return authorities
    }
}