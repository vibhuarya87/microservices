package com.nagarro.auth.controller

import com.nagarro.auth.entity.ApplicationUser
import com.nagarro.auth.repo.ApplicationUserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class AuthController(
    private val userRepo: ApplicationUserRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {

    @PostMapping("/sign_up")
    fun signUp(@RequestBody body: ApplicationUser): ResponseEntity<String> {
        if (userRepo.findByUsername(body.username) != null) {
            return ResponseEntity(HttpStatus.CONFLICT)
        }

        body.password = bCryptPasswordEncoder.encode(body.password)
        userRepo.save(ApplicationUser(username = body.username, password = body.password))
        return ResponseEntity(HttpStatus.CREATED)
    }

}