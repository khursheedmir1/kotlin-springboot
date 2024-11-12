package com.gfiber.useractionevent.controller

import com.gfiber.useractiontracking.service.UserActionService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiController(val userActionService: UserActionService) {

    @GetMapping
    fun index(): String {
        GlobalScope.launch(Dispatchers.IO) {
            userActionService.testProcessAction()
        }
        return "This is test message"
    }

}