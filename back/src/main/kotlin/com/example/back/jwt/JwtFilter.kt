package com.example.back.jwt

import com.example.back.token.AuthToken
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Order(1)
class JwtFilter(private val tokenProvider: TokenProvider) : OncePerRequestFilter() {
    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val httpServletRequest = request as HttpServletRequest
        val jwt = resolveToken(httpServletRequest)
        val requestURI = httpServletRequest.requestURI
        if (StringUtils.hasText(jwt)) {
            val email = tokenProvider.getAuthentication(jwt)
            val authToken=AuthToken(email,false)
            SecurityContextHolder.getContext().setAuthentication(authToken);
            Companion.logger.debug("Security Context에 '{}' 인증 정보를 저장했습니다, email: {}", email, requestURI)
        } else {
            throw Exception()
            Companion.logger.debug("유효한 JWT 토큰이 없습니다, uri: {}", requestURI)
        }
        filterChain.doFilter(httpServletRequest, response)
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(AUTHORIZATION_HEADER)
        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7)
        } else null
    }

    @Throws(ServletException::class)
    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return "/api/user"==request.servletPath
    }

    companion object {
        private val logger = LoggerFactory.getLogger(JwtFilter::class.java)
        const val AUTHORIZATION_HEADER = "Authorization"
    }
}


