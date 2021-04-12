package com.nagarro.auth.repo

import com.nagarro.auth.entity.ApplicationUser
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ApplicationUserRepository : MongoRepository<ApplicationUser, ObjectId> {
    fun findByUsername(username: String): ApplicationUser?
}