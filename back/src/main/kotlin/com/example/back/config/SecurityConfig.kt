package com.example.back.config


import com.example.back.jwt.JwtAuthenticationEntryPoint
import com.example.back.jwt.JwtSecurityConfig
import com.example.back.jwt.TokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.csrf.CookieCsrfTokenRepository


@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    private val tokenProvider: TokenProvider,
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val corsConfig: CorsConfig
    ): WebSecurityConfigurerAdapter() {


    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(web: WebSecurity) {
        web.ignoring()
            .antMatchers(
                "/h2-console/**", "/favicon.ico", "/error"
            )
    }

    @Throws(Exception::class)
    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
            .csrf().disable()

            .addFilterBefore(corsConfig.corsFilter(), UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            .antMatchers("/api/users/authenticate").permitAll()
            .antMatchers(HttpMethod.POST, "/api/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/**").permitAll()
            .antMatchers(HttpMethod.DELETE, "/api/**").permitAll()
            .antMatchers(HttpMethod.PUT, "/api/**").permitAll()
            .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
            .antMatchers("/api/**").permitAll()
            .antMatchers("/socket/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .apply(JwtSecurityConfig(tokenProvider))
        httpSecurity.csrf().ignoringAntMatchers("/api/**", "/socket/**") //csrf예외처리
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
    }
}


