package com.nagarro.stockservice.data

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document
data class StockTransaction(
    @Id val id: ObjectId = ObjectId.get(),
    val updatedAt: LocalDate = LocalDate.now(),
    val type: StockTransactionType,
    val stock: Stock,
    val accountID: String,
    val amount: Double,
    val numberOfStock: Int
)

enum class StockTransactionType {
    BUY,
    SELL,
}