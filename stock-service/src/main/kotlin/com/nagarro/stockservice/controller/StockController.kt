package com.nagarro.stockservice.controller

import com.nagarro.stockservice.StockInAccountRepository
import com.nagarro.stockservice.StockRepository
import com.nagarro.stockservice.StockTransactionRepository
import com.nagarro.stockservice.data.Stock
import com.nagarro.stockservice.data.StockInAccount
import com.nagarro.stockservice.data.StockTransaction
import com.nagarro.stockservice.data.StockTransactionType
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/stock")
class StockController(
    val stockRepo: StockRepository,
    val stockInAccountRepo: StockInAccountRepository,
    val transactionRepo: StockTransactionRepository
) {

    @GetMapping
    fun getStock(@RequestParam name: String?, @RequestParam code: String?): ResponseEntity<*> {
        if (name != null) {
            val stock = stockRepo.findByName(name) ?: return ResponseEntity.badRequest().build<Stock>()
            return ResponseEntity.ok(stock.toString())
        }

        if (code != null) {
            val stock = stockRepo.findByCode(code) ?: ResponseEntity.badRequest().build<Stock>()
            return ResponseEntity.ok(stock.toString())
        }

        val responseBody = stockRepo.findAll().map {
            it.toString()
        }
        return ResponseEntity.ok(responseBody)
    }

    @PostMapping("/sell")
    fun sell(
        @RequestBody accountId: String,
        @RequestBody stockID: String,
        @RequestBody numOfStock: Double
    ): ResponseEntity<String> {
        if (!stockRepo.existsById(ObjectId(stockID))) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        val stockInAccount = stockInAccountRepo.findByAccountIDAndStockID(accountId, stockID)
        if (stockInAccount == null || stockInAccount.numberOfStock - numOfStock < 0.0) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        stockInAccount.numberOfStock = stockInAccount.numberOfStock - numOfStock
        val transaction = StockTransaction(
            numberOfStock = numOfStock,
            accountID = accountId,
            stockID = stockID,
            type = StockTransactionType.SELL
        )

        stockInAccountRepo.save(stockInAccount)
        transactionRepo.save(transaction)

        return ResponseEntity.ok(stockInAccount.toString())
    }

    @PostMapping("/buy")
    fun buy(
        @RequestBody accountId: String,
        @RequestBody stockID: String,
        @RequestBody numOfStock: Double
    ): ResponseEntity<String> {
        if (!stockRepo.existsById(ObjectId(stockID))) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }

        var stockInAccount = stockInAccountRepo.findByAccountIDAndStockID(accountId, stockID)
        if (stockInAccount == null) {
            stockInAccount = StockInAccount(
                stockID = stockID,
                accountID = accountId,
            )
        }
        stockInAccount.numberOfStock = stockInAccount.numberOfStock + numOfStock

        val transaction = StockTransaction(
            numberOfStock = numOfStock,
            accountID = accountId,
            stockID = stockID,
            type = StockTransactionType.BUY
        )

        stockInAccountRepo.save(stockInAccount)
        transactionRepo.save(transaction)

        return ResponseEntity.ok(stockInAccount.toString())
    }

    @GetMapping("/history")
    fun history(
        @RequestBody accountId: String
    ): ResponseEntity<List<String>> {
        val transactions = transactionRepo.findByAccountID(accountId)
        if (transactions.isNullOrEmpty()) {
            return ResponseEntity.noContent().build()
        }

        val responseBody = transactions.map {
            it.toString()
        }
        return ResponseEntity.ok(responseBody)
    }

    @PostMapping("/buy/schedule")
    fun scheduleBuy(
        @RequestBody accountId: String,
        @RequestBody stockID: String,
        @RequestBody numOfStock: Double
    ): ResponseEntity<String> {
        return ResponseEntity.accepted().build()
    }

    @PostMapping("/sell/schedule")
    fun scheduleSell(
        @RequestBody accountId: String,
        @RequestBody stockID: String,
        @RequestBody numOfStock: Double
    ): ResponseEntity<String> {
        return ResponseEntity.accepted().build()
    }
}