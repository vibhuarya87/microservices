package com.nagarro.user.controller

import com.nagarro.user.entity.Account
import com.nagarro.user.entity.ApplicationUser
import com.nagarro.user.repo.AccountRepository
import com.nagarro.user.repo.ApplicationUserRepository
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
    private val accountRepo: AccountRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {

    @PostMapping("/sign_up")
    fun signUp(@RequestBody body: ApplicationUser): ResponseEntity<ApplicationUser> {
        if (userRepo.findByUsername(body.username) != null) {
            return ResponseEntity(HttpStatus.CONFLICT)
        }

        body.password = bCryptPasswordEncoder.encode(body.password)
        val user = userRepo.save(ApplicationUser(username = body.username, password = body.password))
        accountRepo.save(Account(user = user.id))
        return ResponseEntity(HttpStatus.CREATED)
    }

}