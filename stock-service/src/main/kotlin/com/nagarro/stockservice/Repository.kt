package com.nagarro.stockservice

import com.nagarro.stockservice.data.Stock
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface StockRepository: MongoRepository<Stock, ObjectId> {
    fun findByName(name: String): Stock?
}