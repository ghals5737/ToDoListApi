package com.example.back.jwt

import com.example.back.dto.User
import com.example.back.service.UserService
import com.example.back.token.AuthToken
import org.bson.types.ObjectId
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component
import java.util.*


@Component
class TokenAuthProvider(
    val userService: UserService
) : AuthenticationProvider {
    @Throws(AuthenticationException::class)
    override fun authenticate(authToken: Authentication): Authentication {
        if(authToken.credentials as Boolean){
            val user=User().apply{
                this.id= ObjectId.getSmallestWithDate(Date())
                this.userId=authToken.principal as String
                this.regDt= Date()
            }
            userService.createUser(user)
            authToken.isAuthenticated=true
        }else{
            if(!userService.existByUserId(authToken.principal as String)){
                throw Exception()
            }
        }

        return authToken
    }

    override fun supports(authentication: Class<*>?): Boolean {
        // 인증객체에서 선언한 객체는 reflection 을 통해서 해당 객체인지 파악하는데 이용된다.
        return AuthToken::class.java.isAssignableFrom(authentication)
    }
}