package com.nagarro.user.repo

import com.nagarro.user.entity.Account
import com.nagarro.user.entity.ApplicationUser
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : MongoRepository<Account, ObjectId> {
    fun findByUser(userId: ObjectId): List<Account>?
}

@Repository
interface ApplicationUserRepository : MongoRepository<ApplicationUser, ObjectId> {
    fun findByUsername(username: String): ApplicationUser?
}