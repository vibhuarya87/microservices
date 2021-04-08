package com.nagarro.stockservice.controller

import com.nagarro.stockservice.StockRepository
import com.nagarro.stockservice.data.Stock
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/stock")
class StockController(val repo: StockRepository) {

    @GetMapping
    fun getStock(@RequestParam name: String?): ResponseEntity<*> {
        if (repo.count() == 0L) {
            Stock(name = "TSLA", value = 650.43, description = "Tesla")
            Stock(name = "APPL", value = 124.53, description = "Apple")
            Stock(name = "GOOGL", value = 1200.65, description = "Google")
            Stock(name = "MSFT", value = 250.45, description = "Microsoft")
            Stock(name = "AMZN", value = 2300.13, description = "Amazon")
        }

        if (name != null) {
            val stock = repo.findByName(name) ?: return ResponseEntity.badRequest().build<Stock>()
            return ResponseEntity.ok(stock)
        }

        return ResponseEntity.ok(repo.findAll())
    }
}