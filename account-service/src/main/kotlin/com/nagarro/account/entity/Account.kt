package com.nagarro.account.entity

import org.bson.types.ObjectId
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
@EntityScan
data class Account(
    @Id
    val id: ObjectId = ObjectId.get(),
    val user: ObjectId,
    var totalValue: Double = 0.0,
    val type: AccountType = AccountType.GENERAL
)

enum class AccountType {
    GENERAL,
    A_401K,
    ROTH_IRA,
    TRAD_IRA
}