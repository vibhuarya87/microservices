package com.nagarro.auth.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.nagarro.auth.entity.ApplicationUser
import com.nagarro.auth.repo.ApplicationUserRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(
    authManager: AuthenticationManager,
    private val userRepo: ApplicationUserRepository
) : UsernamePasswordAuthenticationFilter() {
    init {
        authenticationManager = authManager
    }


    @Throws(AuthenticationException::class, IOException::class, ServletException::class)
    override fun attemptAuthentication(
        req: HttpServletRequest, res: HttpServletResponse
    ): Authentication {
        val creds = ObjectMapper().readValue(req.inputStream, ApplicationUser::class.java)
        return authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                creds.username,
                creds.password,
                emptyList()
            )
        )
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(
        req: HttpServletRequest,
        res: HttpServletResponse, chain: FilterChain?,
        auth: Authentication
    ) {
        val user = auth.principal as User
        val jwt = Jwts.builder()
            .setSubject(user.username)
            .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS512, SECRET)
            .compact()
        if (req.requestURL.endsWith("login")) {
            val appUser = userRepo.findByUsername(user.username)
            val body = """{"UserId":"${appUser!!.id}"}"""
            res.writer.append(body)
        }
        res.addHeader(HttpHeaders.AUTHORIZATION, "$TOKEN_PREFIX $jwt")
    }
}