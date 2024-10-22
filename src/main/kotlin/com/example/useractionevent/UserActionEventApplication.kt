package com.example.useractionevent

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UserActionEventApplication

fun main(args: Array<String>) {
    System.setProperty("spring.config.name", "application")
    runApplication<UserActionEventApplication>(*args)
}
