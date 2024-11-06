package com.example.useractionevent

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = ["com.example.useractiontracking"])
class UserActionEventApplication

fun main(args: Array<String>) {
    runApplication<UserActionEventApplication>(*args)
}
