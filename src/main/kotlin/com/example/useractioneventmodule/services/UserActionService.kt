package com.example.useractioneventmodule.services

import com.example.useractioneventmodule.entities.UserAction
import com.example.useractioneventmodule.models.ActionDetails

interface UserActionService {

    suspend fun processAction(actionDetails: ActionDetails)

    suspend fun getAction(actionId: String): UserAction?

}
