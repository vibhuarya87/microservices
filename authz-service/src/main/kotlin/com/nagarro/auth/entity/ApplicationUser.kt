package com.nagarro.auth.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class ApplicationUser(
    @Id
    var id: ObjectId = ObjectId.get(),
    @Indexed(unique = true)
    var username: String = "",
    var password: String = "",
)