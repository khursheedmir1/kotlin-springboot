package com.example.useractionevent.controllers

import com.example.useractionevent.services.UserActionService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController(val userActionService: UserActionService) {

    @GetMapping("/")
    fun index(): String {
        return "This is test message"
    }

}