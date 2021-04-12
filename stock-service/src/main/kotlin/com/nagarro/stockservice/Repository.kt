package com.nagarro.stockservice

import com.nagarro.stockservice.data.Stock
import com.nagarro.stockservice.data.StockInAccount
import com.nagarro.stockservice.data.StockTransaction
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface StockRepository: MongoRepository<Stock, ObjectId> {
    fun findByName(name: String): Stock?
    fun findByCode(code: String): Stock?
}

interface StockInAccountRepository: MongoRepository<StockInAccount, ObjectId> {
    fun findByAccountIDAndStockID(accountID: String, stockId: String): StockInAccount?
}

interface StockTransactionRepository: MongoRepository<StockTransaction, ObjectId> {
    fun findByAccountID(accountID: String): List<StockTransaction>?
}