package com.nagarro.account.controller

import com.nagarro.account.entity.Account
import com.nagarro.account.entity.AccountType
import com.nagarro.account.repo.AccountRepository
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/account")
class AccountController(
    private val accountRepo: AccountRepository,
) {

    @PostMapping
    fun createAccount(
        @RequestHeader header: Map<String, String>,
        @RequestParam type: String
    ): ResponseEntity<String> {
        val userId = ObjectId(header["userid"])
        val accountType = try {
            AccountType.valueOf(type)
        } catch (ex: Exception) {
            return ResponseEntity("$type is not a valid Account Type", HttpStatus.BAD_REQUEST)
        }
        val accounts = accountRepo.findByUserAndType(userId = userId, type = accountType)
        if (!accounts.isNullOrEmpty()) {
            return ResponseEntity(HttpStatus.CONFLICT)
        }

        val account = accountRepo.save(
            Account(
                user = userId,
                type = accountType
            )
        )

        return ResponseEntity.ok(account.toString())
    }

    @GetMapping
    fun getAccount(
        @RequestHeader header: Map<String, String>,
        @RequestParam type: String?
    ): ResponseEntity<List<String>> {
        val userId = ObjectId(header["userid"])
        val accountType = try {
            AccountType.valueOf(type!!)
        } catch (ex: Exception) {
            null
        }
        val accounts = accountRepo.findByUserAndType(userId = userId, type = accountType)
        if (accounts.isNullOrEmpty()) {
            return ResponseEntity(HttpStatus.NO_CONTENT)
        }
        val response = accounts.map {
            it.toString()
        }
        return ResponseEntity.ok(response)
    }

    @PutMapping
    fun updateValue(
        @RequestParam accountId: String,
        @RequestParam value: Double
    ): ResponseEntity<String> {
        val optional = accountRepo.findById(ObjectId(accountId))
        if (!optional.isPresent) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        val account = optional.get()
        account.totalValue = value
        accountRepo.save(account)

        return ResponseEntity.accepted().build()
    }
}