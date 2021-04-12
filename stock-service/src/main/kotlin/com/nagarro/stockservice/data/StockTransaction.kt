package com.nagarro.stockservice.data

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document
data class StockTransaction(
    @Id val id: ObjectId = ObjectId.get(),
    val type: StockTransactionType,
    val stockID: String,
    val accountID: String,
    val numberOfStock: Double
)

enum class StockTransactionType {
    BUY,
    SELL,
}