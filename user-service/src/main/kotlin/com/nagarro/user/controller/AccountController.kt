package com.nagarro.user.controller

import com.nagarro.user.entity.Account
import com.nagarro.user.entity.ApplicationUser
import com.nagarro.user.repo.AccountRepository
import com.nagarro.user.repo.ApplicationUserRepository
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.annotation.Id
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/account")
class AccountController(
    private val accountRepo: AccountRepository,
    private val userRepo: ApplicationUserRepository,
) {

    @GetMapping
    fun getAccount(@RequestParam userId: String): ResponseEntity<List<AccountResponse>> {
        val accounts = accountRepo.findByUser(userId = ObjectId(userId))
        if (accounts.isNullOrEmpty()) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        val responseBody = accounts.map {
            AccountResponse(
                id = it.id.toHexString(),
                userName = userRepo.findByIdOrNull(ObjectId(userId))!!.username,
                value = it.totalValue
            )
        }
        return ResponseEntity.ok(responseBody)
    }
}

data class AccountResponse(
    val id: String,
    val userName: String,
    val value: Double
)