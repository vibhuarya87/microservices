package com.nagarro.stockservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient

@EnableDiscoveryClient
@SpringBootApplication
class StockExchangeApplication

fun main(args: Array<String>) {
    runApplication<StockExchangeApplication>(*args)
}

