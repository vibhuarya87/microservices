package com.nagarro.auth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.zuul.EnableZuulProxy
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
@EnableMongoRepositories("com.nagarro.auth.repo")
class AuthzServiceApplication

fun main(args: Array<String>) {
    runApplication<AuthzServiceApplication>(*args)
}

@RestController
@RequestMapping
class ApiGatewayController {
    @GetMapping("index")
    fun userService(): ResponseEntity<String> {
        return ResponseEntity.ok("Hello from authz-service")
    }
}