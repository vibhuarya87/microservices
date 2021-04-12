package com.nagarro.auth.entity

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
    val totalValue: Double = 0.0,
)