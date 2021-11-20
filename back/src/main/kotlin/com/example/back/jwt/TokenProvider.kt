package com.example.back.jwt

import com.auth0.jwt.JWT
import com.auth0.jwt.exceptions.JWTDecodeException
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SecurityException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.Key


@Component
class TokenProvider(
    @Value("\${jwt.secret}") secret: String,
    @Value("\${jwt.token-validity-in-seconds}") tokenValidityInSeconds: Long
) :
    InitializingBean {
    private val logger = LoggerFactory.getLogger(TokenProvider::class.java)
    private val secret: String
    private val tokenValidityInMilliseconds: Long
    private var key: Key? = null
    override fun afterPropertiesSet() {
        val keyBytes = Decoders.BASE64.decode(secret)
        key = Keys.hmacShaKeyFor(keyBytes)
    }

    fun getAuthentication(token: String?): String {
        try {
            val jwt = JWT.decode(token)
            return jwt.claims.get("email").toString()
        } catch (exception: JWTDecodeException) {
            //Invalid token
        }
        return "a"
    }

    fun validateToken(token: String?): Boolean {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(token)
            return true
        } catch (e: SecurityException) {
            logger.info("잘못된 JWT 서명입니다.")
        } catch (e: MalformedJwtException) {
            logger.info("잘못된 JWT 서명입니다.")
        } catch (e: ExpiredJwtException) {
            logger.info("만료된 JWT 토큰입니다.")
        } catch (e: UnsupportedJwtException) {
            logger.info("지원되지 않는 JWT 토큰입니다.")
        } catch (e: IllegalArgumentException) {
            logger.info("JWT 토큰이 잘못되었습니다.")
        }
        return false
    }

    companion object {
        private const val AUTHORITIES_KEY = "auth"
    }

    init {
        this.secret = secret
        tokenValidityInMilliseconds = tokenValidityInSeconds * 1000
    }
}