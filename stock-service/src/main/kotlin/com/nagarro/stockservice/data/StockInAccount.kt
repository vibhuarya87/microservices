package com.nagarro.stockservice.data

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class StockInAccount(
    @Id val id: ObjectId = ObjectId.get(),
    val stock: Stock,
    val accountID: String,
    val numberOfStock: Double
)
