package com.gfiber.useractionevent.controller

import com.gfiber.useractiontracking.service.UserActionService
import com.gfiber.useractiontracking.model.ActionDetails
import com.google.cloud.Timestamp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ApiController(val userActionService: UserActionService) {

    @GetMapping
    fun index(): String {
        GlobalScope.launch(Dispatchers.IO){
        val actionDetails = ActionDetails(
            userId = "test-user-id",
            sessionId = "test-session-id",
            traceId = "test-trace-5",
            globalAddressId = "test-global-address-id",
            ipAddress = "127.0.0.1",
            timestamp = Timestamp.now(),
            actionType = "TEST_ACTION",
            currentStep = "TEST_STEP",
            selectionData = mapOf("key1" to "value1"),
            errorCode = "",
            errorMessage = ""
        )
            userActionService.processAction(actionDetails)
        }
        return "This is test message"
    }

}
