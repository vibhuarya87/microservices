package com.nagarro.stockservice

import com.nagarro.stockservice.data.Stock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.context.annotation.Bean


@EnableDiscoveryClient
@SpringBootApplication
class StockExchangeApplication : CommandLineRunner {

    @Autowired
    lateinit var repo: StockRepository

    override fun run(vararg args: String?) {
        if (repo.count() == 0L) {
            repo.save(Stock(code = "TSLA", value = 650.43, name = "Tesla"))
            repo.save(Stock(code = "APPL", value = 124.53, name = "Apple"))
            repo.save(Stock(code = "GOOGL", value = 1200.65, name = "Google"))
            repo.save(Stock(code = "MSFT", value = 250.45, name = "Microsoft"))
            repo.save(Stock(code = "AMZN", value = 2300.13, name = "Amazon"))
        }
    }
}

fun main(args: Array<String>) {
    runApplication<StockExchangeApplication>(*args)
}

