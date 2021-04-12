package com.nagarro.account.repo

import com.nagarro.account.entity.Account
import com.nagarro.account.entity.AccountType
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : MongoRepository<Account, ObjectId> {
    fun findByUserAndType(userId: ObjectId, type: AccountType?): List<Account>?
}