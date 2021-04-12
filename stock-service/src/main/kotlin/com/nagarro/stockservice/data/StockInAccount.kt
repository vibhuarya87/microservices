package com.nagarro.stockservice.data

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class StockInAccount(
    @Id val id: ObjectId = ObjectId.get(),
    val stockID: String,
    val accountID: String,
    var numberOfStock: Double = 0.0
)
