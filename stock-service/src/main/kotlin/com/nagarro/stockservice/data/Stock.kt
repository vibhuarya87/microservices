package com.nagarro.stockservice.data

import org.bson.types.ObjectId
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
@EntityScan
data class Stock(
    @Id
    val id: ObjectId = ObjectId.get(),
    var name: String = "",
    val value: Double = 0.0,
    var description: String = ""
)
