package com.nagarro.user

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@EnableEurekaClient
@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
@EnableMongoRepositories("com.nagarro.user.repo")
class UserApplication

fun main(args: Array<String>) {
    runApplication<UserApplication>(*args)
}

@RestController
@RequestMapping
class UserController {
    @GetMapping("index")
    fun userService(): ResponseEntity<String> {
        return ResponseEntity.ok("Hello from User-Service")
    }
}