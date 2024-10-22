package com.example.useractionevent.services

import com.example.useractionevent.entities.UserAction
import com.example.useractionevent.models.ActionDetails


interface UserActionService {

    suspend fun processAction(actionDetails: ActionDetails)

    suspend fun getAction(actionId: String): UserAction?

}
